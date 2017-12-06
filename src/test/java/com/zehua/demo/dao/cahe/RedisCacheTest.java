package com.zehua.demo.dao.cahe;

import com.zehua.DemoWebApplication;
import com.zehua.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoWebApplication.class)
public class RedisCacheTest {
    @Resource
    private RedisTemplate<String, User> redisTemplate;

    /**
     * 缓存测试
     * @throws Exception
     */
    @Test
    public void testRedisTemplate() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("步惊云");
        redisTemplate.opsForValue().set("test",user);

        User retUser ;
        retUser = redisTemplate.opsForValue().get("test");

        Assert.assertTrue(retUser!=null);
    }

    /**
     * 缓存测试，缓存有延迟
     * @throws Exception
     */
    @Test
    public void testRedisTemplateGet() throws Exception {
        User retUser ;
        retUser = redisTemplate.opsForValue().get("test");

        System.out.println(retUser);

    }
}