package com.yuanchuang.demo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuanchuang.demo.dao.UserPojoMapper;
import com.yuanchuang.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author :zyh
 * create 2020-05-12
 */
@Service
public class UserService {

    @Autowired
    private UserPojoMapper userPojoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    public PageInfo<User> queryAll(int currentPage, int pageSize){
        // 调用分页插件
        PageHelper.startPage(currentPage, pageSize);
        List<User> list=userPojoMapper.queryAll();
        // 把list封装到怕pageinfo
        PageInfo<User> pageInfo=new PageInfo<User>(list);
        return  pageInfo;
    }
    /**
     * 获取用户的策略：先从缓存中获取用户，没有则取数据表中 数据，再将数据写入缓存
     */
    public User findUserById(int id){
        String key = "user_" + id;
        // 对redis字符串类型数据操作,使其可存对象
        ValueOperations<String, User> operations = redisTemplate.opsForValue();

        // 判断redis中是否有键为key的缓存
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            // 如果redis中有的话，从redis缓存中获取
            User user = operations.get(key);
            System.out.println("从redis缓存中获取的数据:"+user.getUsername());
            System.out.println("==============================================");
            return  user;
        } else {
            // 缓存中无从数据库查
            User user = userPojoMapper.findUserById(id);
            System.out.println("查询数据库得到数据:"+user.getUsername());
            System.out.println("==============================================");

            // 解决redis缓存穿透，在数据库查到不为空的时候写入缓存
            if (user != null) {
                // 写入redis缓存,设置过期时间五小时
                operations.set(key, user, 5, TimeUnit.HOURS);
            } else {
               // 查询为空，也写入缓存 ,设置值为空，过期时间很短
                operations.set(key, null, 60, TimeUnit.SECONDS);
            }
            return  user;
        }
    }

    /**
     * 更新修改用户策略，更新数据库成功之后，先删除缓存中的再新增
     */
    public  int updateUser(User user){
        // 对redis字符串类型数据操作,使其可存对象
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        int resultUserId=userPojoMapper.updateUser(user);
        if (resultUserId != 0) {
            String key = "user_" + user.getId();
            // 还是判断一下redis缓存中是否有记录
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("删除缓存中的key---------------》"+key);
            }
            // 再将更新后的数据加入缓存
            User newUser = userPojoMapper.findUserById(user.getId());
            if (newUser != null) {
                operations.set(key, newUser, 3, TimeUnit.HOURS);
            }
        }
        return resultUserId;
    }
    /**
     * 删除用户策略：删除数据表中数据，然后删除缓存
     */
    public int deleteUserById(int id){
        int resultUserId = userPojoMapper.deleteUserById(id);
        String key = "user_"+id;
        if (resultUserId != 0) {
            boolean hasKey = redisTemplate.hasKey(key);
            if (hasKey) {
                redisTemplate.delete(key);
                System.out.println("从redis缓存中删除key----------------");
            }
        }
        return  resultUserId;
    }
}
