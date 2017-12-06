package com.zehua.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * MyBatis配置类
 */
@Configuration
public class MyBatisMapperScannerConfig {


    @Bean
    @DependsOn("applicationContextHolder")
    public MapperScannerConfigurer mapperScannerConfigurer() {

        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage("com.zehua.demo.dao");
        Properties properties = new Properties();
        properties.setProperty("mappers", "com.zehua.demo.common.BaseDao");
        properties.setProperty("notEmpty", "false");
        properties.setProperty("IDENTITY", "MYSQL");
        mapperScannerConfigurer.setProperties(properties);
        return mapperScannerConfigurer;
    }

}