package com.yuanchuang.demo.dao;



import com.yuanchuang.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserPojoMapper {
    @Select("select * from user")
    List<User> queryAll();

    @Select("select * from user where id = #{id}")
    User findUserById(int id);

    @Update("UPDATE USER SET username = #{username} ,PASSWORD = #{password},salary = #{salary} WHERE id = #{id}")
    int updateUser(User user);

    @Delete("delete from user where uid = #{id}")
    int deleteUserById(int id);
}