package com.xybert.springbootmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xybert
 * @description spring-boot-mybatis启动类
 * @date 2022/10/18 11:28
 */

@SpringBootApplication
@MapperScan("com.xybert.springbootmybatis.mapper")
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }

}
