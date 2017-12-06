package com.zehua.demo.dao;

import com.zehua.DemoWebApplication;
import com.zehua.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoWebApplication.class)
public class UserDaoTest {
    @Autowired
    UserDao userDao;
    @Test
    public void selectById() throws Exception {
        int id = 5;
        User user = userDao.selectById(id);
        System.out.println(user.getName());
    }

    @Test
    public void allUser() throws Exception {
    }

    @Test
    public void insert() throws Exception {
    }

    @Test
    public void deleteById() throws Exception {
    }

    @Test
    public void updateT() throws Exception {
    }

}