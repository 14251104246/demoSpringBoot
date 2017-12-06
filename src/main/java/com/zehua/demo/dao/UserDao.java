package com.zehua.demo.dao;

import com.zehua.demo.common.BaseDao;
import com.zehua.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@CacheNamespace(implementation=com.zehua.demo.dao.cahe.RedisCache.class)
public interface UserDao extends BaseDao<User> {

    @Select("select * from user where id = #{id}")
    public User selectById(int id);

    @Select("select * from user")
    public List<User> allUser();

    @Insert("insert into User(name) values(#{name})")
    @Options(useGeneratedKeys=true, keyProperty="User.id")
    public int insertUser(User user);

    @Delete("delete from User where id=#{id}")
    public void deleteUserById(int id);

    @Update("update User set name=#{name} where id=#{id}")
    public void updateUser(User user);
}