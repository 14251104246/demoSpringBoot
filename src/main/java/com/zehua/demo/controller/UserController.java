package com.zehua.demo.controller;


import com.zehua.demo.dao.UserDao;
import com.zehua.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDao userDao;
    /**
     * 增
     * http://localhost:8080/addUser/郝启
     */
    @RequestMapping(value = "/addUser/{userName}",
            produces = {"application/json;charset=UTF-8"})
    public List<User> addUser(@PathVariable("userName") String userName){
        User user = new User();
        user.setName(userName);

        userDao.insertUser(user);

        System.out.println("/addUser");

        return allUsers();
    }

    /**
     * 删
     * http://localhost:8080/delUser/9
     */
    @RequestMapping(value = "/delUser/{id}",
            produces = {"application/json;charset=UTF-8"})
    public List<User> delUser(@PathVariable("id") int id){
        userDao.deleteUserById(id);

        System.out.println("/delUser");

        return allUsers();
    }

    /**
     * 改
     * http://localhost:8080/updateUser/9/天山
     */
    @RequestMapping(value = "/updateUser/{id}/{newName}",
            produces = {"application/json;charset=UTF-8"})
    public List<User> updateUser(@PathVariable("id") int id,@PathVariable("newName") String newName){
        User user = new User();
        user.setId(id);
        user.setName(newName);

        userDao.updateUser(user);

        System.out.println("/updateUser");

        return allUsers();
    }


    /**
     * 查
     * http://localhost:8080/searchUser/9
     */
    @RequestMapping(value = "/searchUser/{id}",
            produces = {"application/json;charset=UTF-8"})
    public User searchUser(@PathVariable("id") int id){
        User user = userDao.selectById(id);

        System.out.println(user);

        return user;
    }

    @RequestMapping(value = "/allUser",
            produces = {"application/json;charset=UTF-8"})
    public List<User> allUsers() {
        return userDao.allUser();
    }
}
