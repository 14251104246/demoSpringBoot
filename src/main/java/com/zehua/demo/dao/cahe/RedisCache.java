package com.zehua.demo.dao.cahe;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.zehua.demo.util.ApplicationContextHolder;
import org.apache.ibatis.cache.Cache;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;


public class RedisCache implements Cache{

    private static Logger logger = LogManager.getLogger(RedisCache.class);//日志管理
    private String id;
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock(); //读写锁
    private static final long EXPIRE_TIME_IN_MINUTES = 30; // redis过期时间

    protected RedisTemplate<String, Object> redisTemplate ;



    public void setReadWriteLock(ReadWriteLock readWriteLock) {
        this.readWriteLock = readWriteLock;
    }

    public RedisCache(final String id) {
        if(id==null){
            throw new IllegalArgumentException("Cache instance requires an ID");
        }
        logger.debug("create an cache instance with id"+id);
        this.id=id;
    }
    @Override
    public String getId() {
        return this.id;
    }


    @Override
    public void putObject(Object key, Object value) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            opsForValue.set(key, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES);
            logger.debug("Put query result to redis");
        }
        catch (Throwable t) {
            logger.error("Redis put failed", t);
        }
    }


    @Override
    public Object getObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            ValueOperations opsForValue = redisTemplate.opsForValue();
            logger.debug("Get cached query result from redis");
            return opsForValue.get(key);
        }
        catch (Throwable t) {
            logger.error("Redis get failed, fail over to db", t);
            return null;
        }
    }
    @Override
    public Object removeObject(Object key) {
        try {
            RedisTemplate redisTemplate = getRedisTemplate();
            redisTemplate.delete(key);
            logger.debug("Remove cached query result from redis");
        }
        catch (Throwable t) {
            logger.error("Redis remove failed", t);
        }
        return null;
    }
    @Override
    public void clear() {
        redisTemplate.execute(new RedisCallback() {
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
        return ;
    }
    @Override
    public int getSize() {

        Long size = redisTemplate.execute(new RedisCallback<Long>() {
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.dbSize();
            }
        });
        int s = (int) (size%Integer.MAX_VALUE);
        return s;
    }
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}