package com.zehua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoWebApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoWebApplication.class, args);
    }
}
