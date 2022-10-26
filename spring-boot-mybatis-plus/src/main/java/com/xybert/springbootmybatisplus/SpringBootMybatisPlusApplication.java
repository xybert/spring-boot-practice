package com.xybert.springbootmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xybert
 * @description spring-boot-mybatis-plus启动类
 * @date 2022/10/21 10:35
 */
@SpringBootApplication
@MapperScan("com.xybert.springbootmybatisplus.mapper")
public class SpringBootMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisPlusApplication.class, args);
    }

}
