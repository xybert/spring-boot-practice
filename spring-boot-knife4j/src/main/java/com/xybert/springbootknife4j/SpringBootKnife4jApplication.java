package com.xybert.springbootknife4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author xybert
 * @description spring-boot-knife4j 启动类
 * @date 2022/11/11 10:19
 */

@SpringBootApplication
@EnableWebMvc
public class SpringBootKnife4jApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootKnife4jApplication.class, args);
    }

}
