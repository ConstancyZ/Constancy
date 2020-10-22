package com.mpdemo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mpdemo.dao.TKUserMapper;
import com.mpdemo.entity.TKUser;
import com.mpdemo.service.TKUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.WebEndpoint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private TKUserMapper tkUserMapper;
    @RequestMapping("/getUser")
    public TKUser getUser () {

        return tkUserMapper.selectById(736);
    }
    @RequestMapping("/addUser")
    public Long addUser () {
        TKUser tkUser = new TKUser();
        tkUser.setMobile("12345678965");
        tkUser.setEmail("@163.com");
        tkUser.setKdgtTabId(Long.parseLong("62"));
        tkUser.setAddress("hujian");
        tkUserMapper.insert(tkUser);
       return tkUser.getId();
    }
    @RequestMapping("/updateUser")
    public void updateUser(){
        TKUser tkUser = new TKUser();
        tkUser.setId(Long.parseLong("736"));
        tkUser.setEmail("@163.com");
        tkUserMapper.updateById(tkUser);//根据id进行更新，没有传值的属性就不会更新
        //tkUserMapper.;//根据id进行更新，没传值的属性就更新为null
    }
    @RequestMapping("/selectByCondition")
    public List<TKUser> selectByCondition(){
        /*
        查询条件用map集合封装，columnMap，写的是数据表中的列名，
        而非实体类的属性名。比如属性名为lastName，数据表中字段为last_name，
        这里应该写的是last_name。selectByMap方法返回值用list集合接收
         */
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("mobile","13882985800");//写表中的列名
        columnMap.put("name","Zay");
        // 查询出满足columnMap条件的所有user
        List<TKUser> tkUsers = tkUserMapper.selectByMap(columnMap);
        return tkUsers;
    }
    @RequestMapping("/selectByIds")
    public List<TKUser> selectByIds(){
        /*
       通过id批量查询
         */
        List<Integer> idList = new ArrayList<>();
        idList.add(734);
        idList.add(735);
        idList.add(736);
        // 查询出满足columnMap条件的所有user
        List<TKUser> tkUsers = tkUserMapper.selectBatchIds(idList);
        return tkUsers;
    }
    @RequestMapping("/selectByPage")
    public List<TKUser> selectByPage(){
        /*分页查询
        selectPage方法就是分页查询，在page中传入分页信息，后
        者为null的分页条件，这里先让其为null，讲了条件构造器再说其用法。
        这个分页其实并不是物理分页，而是内存分页。也就是说，查询的时候
        并没有limit语句。等配置了分页插件后才可以实现真正的分页。
         */

        // 查询出满足columnMap条件的所有user
        List<TKUser> tkUsers = (List<TKUser>) tkUserMapper.selectPage(new Page<>(1,2),null);
        return tkUsers;
    }
    @RequestMapping("/deleteByCondition")
    public  void deleteByCondition(){
        Map<String,Object> columnMap = new HashMap<>();
        columnMap.put("mobile","13882985800");//写表中的列名
        columnMap.put("name","Zay");
        tkUserMapper.deleteByMap(columnMap);
        // 按id批量删除与selectByIds一致
    }
    /**
     * 条件构造器(EntityWrapper)：
     * 以上基本的 CRUD 操作，我们仅仅需要继承一个 BaseMapper 即可实现大部分单表 CRUD 操作。
     * BaseMapper 提供了多达 17 个方法供使用, 可以极其方便的实现单一、批量、分页等操作，
     * 极大的减少开发负担。但是mybatis-plus的强大不限于此，请看如下需求该如何处理：
     * 需求：
     * 我们需要分页查询 tb_employee 表中，年龄在 18~50 之间性别为男且姓名为 xx 的所有用户
     * 这时候我们该如何实现上述需求呢？
     * 使用MyBatis : 需要在 SQL 映射文件中编写带条件查询的 SQL,并用PageHelper 插件完成分页.
     * 实现以上一个简单的需求，往往需要我们做很多重复单调的工作。
     * 使用MP: 依旧不用编写 SQL 语句，MP 提供了功能强大的条件构造器 ------ EntityWrapper。
     *
     * 接下来就直接看几个案例体会EntityWrapper的使用。
     */
     // 查询gender为0且名字中带有老师、或者邮箱中带有a的用户：
    @RequestMapping("selectByEntityWrapper")
    public IPage<TKUser> selectByEntityWrapper(){
        // 分页查询园所id在1-100之间，state在1，性别为男的用户
        IPage<TKUser> tkUsers = tkUserMapper.selectPage(new Page<TKUser>(1,3),
                new QueryWrapper<TKUser>()
                        .between("kdgt_tab_id",1,100)
                        .eq("state",1)
                        .eq("sex","男"));

        return tkUsers;
        /*
        由此案例可知，分页查询和之前一样，new 一个page对象传入分页信息即可。至于分页条件，
        new 一个QueryWrapper对象，调用该对象的相关方法即可。between方法三个参数，
        分别是column、value1、value2，该方法表示column的值要在value1和value2之间；
        eq是equals的简写，该方法两个参数，column和value，表示column的值和value要相等。
        注意column是数据表对应的字段，而非实体类属性字段。
         */
    }
    // 模糊查询
    @RequestMapping("selectByLike")
    public List<TKUser> selectByLike(){
        List<TKUser> tkUsers = tkUserMapper.selectList(
                new QueryWrapper<TKUser>()
                        .eq("state",1)
                .like("mobile","138")
                .or()
                .like("name","Zay")
        );
            return  tkUsers;
    }
    // 查询state为1，根据birthdate排序，简单分页
    @RequestMapping("selectByOrder")
    public List<TKUser> selectByOrder() {
        List<TKUser> tkUsers = tkUserMapper.selectList(
                new QueryWrapper<TKUser>()
                .eq("state",1)
                .orderByDesc("birthdate")
                .last("limit 1,3"));
        return tkUsers;
        /*
        简单分页是指不用page对象进行分页。orderBy方法就是根据传入的column进行升序排序，
        若要降序，可以使用orderByDesc方法，也可以如案例中所示用last方法；last方法就是
        将last方法里面的value值追加到sql语句的后面，在该案例中，最后的sql语句就变为
        select ······ order by desc limit 1, 3，追加了desc limit 1,3所以可以进行降序排序和分页。
         */
    }
    /*
    根据条件更新
     */
    @RequestMapping("/updateByCondition")
    public void updateByCondition(){
        TKUser tkUser = new TKUser();
        tkUser.setName("zyh");
        tkUserMapper.update(tkUser,
                new UpdateWrapper<TKUser>()
                .eq("mobile","13882985800")
        );
        // 吧mobile13882985800的用户name改成zyh
    }
}
