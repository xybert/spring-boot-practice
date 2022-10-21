# spring-boot-mybatis

## 简述

该模块展示 Spring Boot 集成原生的 MyBatis，使用 MyBatis 官方提供的脚手架 mybatis-spring-boot-starter。

MyBatis 官方文档：[https://mybatis.org/mybatis-3/zh/index.html](https://mybatis.org/mybatis-3/zh/index.html)

MyBatis 官方脚手架文档：[http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/](http://mybatis.org/spring-boot-starter/mybatis-spring-boot-autoconfigure/)
### 插件：MyBatisX

使用文档：[https://baomidou.com/pages/ba5b24/](https://baomidou.com/pages/ba5b24/)

源码：[https://github.com/baomidou/MybatisX](https://github.com/baomidou/MybatisX)

MyBatisX 是一款基于 IDEA 的快速开发插件，在使用 MyBatis 和 MyBatis-plus 开发时简化繁琐的重复操作，提高开发效率。

## 数据库

### 创建表

#### user.init.sql

```mysql
-- 创建数据库 spring-boot-practice
CREATE DATABASE IF NOT EXISTS `spring-boot-practice` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `spring-boot-practice`;
-- 创建表 user
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(50) NOT NULL COMMENT '用户名',
    `sex`         integer(2)  NOT NULL COMMENT '性别 1-男 0-女',
    `age`         integer(5)  NOT NULL COMMENT '年龄',
    `tel`         varchar(20)          DEFAULT '' COMMENT '联系电话',
    `email`       varchar(50)          DEFAULT '' COMMENT '邮箱',
    `status`      integer(2)  NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `role`        integer(2)  NOT NULL DEFAULT 3 COMMENT '角色 1-超级管理员 2-管理员 3-普通用户',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    CONSTRAINT user_name_uindex
        UNIQUE (name),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '用户表';
```

## 代码

### pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.4</version>
    </parent>

    <groupId>com.xybert</groupId>
    <artifactId>spring-boot-mybatis</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-mybatis</name>
    <description>spring-boot-mybatis</description>

    <properties>
        <java.version>1.8</java.version>
        <mybatis.spring.version>2.2.2</mybatis.spring.version>
        <mybatis.version>3.5.11</mybatis.version>
        <druid.version>1.2.12</druid.version>
        <hutool.version>5.8.8</hutool.version>
        <commons-collections4.version>4.4</commons-collections4.version>
        <guava.version>31.1-jre</guava.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- mybatis -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>${mybatis.spring.version}</version>
        </dependency>
<!--        <dependency>-->
<!--            <groupId>org.mybatis</groupId>-->
<!--            <artifactId>mybatis</artifactId>-->
<!--            <version>${mybatis.version}</version>-->
<!--        </dependency>-->

        <!-- mysql -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>

        <!-- druid -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>${druid.version}</version>
        </dependency>

        <!-- lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>

        <!-- hutool -->
        <dependency>
            <groupId>cn.hutool</groupId>
            <artifactId>hutool-all</artifactId>
            <version>${hutool.version}</version>
        </dependency>

        <!-- commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
        </dependency>

        <!-- commons-collections4 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-collections4</artifactId>
            <version>${commons-collections4.version}</version>
        </dependency>

        <!-- guava 工具库 -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>${guava.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>2.7.4</version>
            </plugin>
        </plugins>
    </build>

</project>
```

### application.yml

```yaml
server:
  port: 9001
  servlet:
    context-path: /springboot/practice/mybatis

spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/spring-boot-practice?useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&autoReconnect=true&serverTimezone=GMT%2B8
    username: root
    password: 123456
    type: com.alibaba.druid.pool.DruidDataSource

mybatis:
  configuration:
    map-underscore-to-camel-case: true
  mapper-locations: classpath:mapper/*Mapper.xml
```

### SpringBootMyBatisApplication.class

```java
package com.xybert.springbootmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description spring-boot-mybatis启动类
 * @author xybert
 * @date 2022/10/18 11:28
 */
@SpringBootApplication
public class SpringBootMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisApplication.class, args);
    }
}
```

### User.class

```java
package com.xybert.springbootmybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * @description User 实体类
 * @author xybert
 * @date  2022/10/18 14:42
 */

@Component
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    /**
     * 逐渐
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别 1-男 0 女
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 1-启用 0-禁用
     */
    private Integer status;

    /**
     * 角色 1-超级管理员 2-管理员 3-普通用户
     */
    private Integer role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
```

### UserMapper.class

```java
package com.xybert.springbootmybatis.mapper;

import com.xybert.springbootmybatis.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description user表访问层
 * @author xybert
 * @date 2022/10/18 14:50
 */

@Mapper
public interface UserMapper {

    /**
     * 查询所有用户
     *
     * @return List<User> 用户列表
     */
    @Select("SELECT * FROM user")
    List<User> selectAllUser();

    /**
     * 根绝id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUserById(@Param("id") Long id);

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    @Select("SELECT * FROM user WHERE name = #{name}")
    User selectUserByName(@Param("name") String name);

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 1-成功 0-失败
     */
    int insertUser(@Param("user") User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 1-成功 0-失败
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(@Param("id") Long id);

    /**
     * 修改用户信息
     *
     * @param user 用户
     * @return 1-成功 0-失败
     */
    int updateUser(@Param("user") User user);
}
```

### UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.xybert.springbootmybatis.mapper.UserMapper">

    <insert id="insertUser">
        INSERT INTO user
        <trim prefix="(" suffix=")" suffixOverrides=",">
            name, sex, age,
            <if test='user != null and user.tel != null and user.tel != ""'>
                tel,
            </if>
            <if test='user != null and user.email != null and user.email != ""'>
                email,
            </if>
            <if test='user != null and user.status != null and user.status &gt;= 0 and user.status &lt;= 1'>
                status,
            </if>
            <if test='user != null and user.role != null and user.role &gt;= 1 and user.role &lt;= 3'>
                role,
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            #{user.name}, #{user.sex}, #{user.age},
            <if test='user != null and user.tel != null and user.tel != ""'>
                #{user.tel},
            </if>
            <if test='user != null and user.email != null and user.email != ""'>
                #{user.email},
            </if>
            <if test='user != null and user.status != null and user.status &gt;= 0 and user.status &lt;= 1'>
                #{user.status},
            </if>
            <if test='user != null and user.role != null and user.role &gt;= 1 and user.role &lt;= 3'>
                #{user.role},
            </if>
        </trim>
    </insert>

    <update id="updateUser">
        UPDATE user
        <trim prefix="set" suffixOverrides=",">
            <if test='user != null and user.name != null and user.name != ""'>
                name = #{user.name},
            </if>
            <if test='user != null and user.sex != null and user.sex &gt;= 0 and user.sex &lt;= 1'>
                sex = #{user.sex},
            </if>
            <if test='user != null and user.age != null and user.age &gt; 0'>
                age = #{user.age},
            </if>
            <if test='user != null and user.tel != null and user.tel != ""'>
                tel = #{user.tel},
            </if>
            <if test='user != null and user.email != null and user.email != ""'>
                email = #{user.email},
            </if>
            <if test='user != null and user.status != null and user.status &gt;= 0 and user.status &lt;= 1'>
                status = #{user.status},
            </if>
            <if test='user != null and user.role != null and user.role &gt;= 1 and user.role &lt;= 3'>
                role = #{user.role},
            </if>
        </trim>
        WHERE id = #{user.id}
    </update>

</mapper>
```

### UserService.class

```java
package com.xybert.springbootmybatis.service;

import com.xybert.springbootmybatis.entity.User;

import java.util.List;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:39
 */

public interface UserService {

    /**
     * 查询所有用户信息
     *
     * @return List<User> 用户列表
     */
    List<User> listUsers();

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    User selectUserById(Long id);

    /**
     * 根绝用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    User selectUserByName(String name);

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User 用户
     */
    User insertUser(User userInfo);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true-成功 false-失败
     */
    User deleteUserById(Long id);

    /**
     * 修改用户信息
     * 成功则返回修改后的信息，失败返回修改前的信息
     *
     * @param user 用户
     * @param id 用户id
     * @return User 用户
     */
    User updateUser(User user, Long id);
}
```

### UserServiceImpl.class

```java
package com.xybert.springbootmybatis.service.impl;

import cn.hutool.system.UserInfo;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.enums.UserOperateEnum;
import com.xybert.springbootmybatis.exception.CustomException;
import com.xybert.springbootmybatis.mapper.UserMapper;
import com.xybert.springbootmybatis.service.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:40
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    /**
     * 查询所有用户信息
     *
     * @return List<User> 用户列表
     */
    @Override
    public List<User> listUsers() {
        List<User> users = userMapper.selectAllUser();
        if (CollectionUtils.isEmpty(users)) {
            return Lists.newArrayList();
        }
        return users;
    }

    /**
     * 根据id查询用户信息
     *
     * @param id 用户id
     * @return User 用户
     */
    @Override
    public User selectUserById(Long id) {
        return Optional.ofNullable(userMapper.selectUserById(id))
                .orElseThrow(() -> new CustomException(UserOperateEnum.USER_NOT_EXIST));
    }

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return User 用户
     */
    @Override
    public User selectUserByName(String name) {
        return Optional.ofNullable(userMapper.selectUserByName(name))
                .orElseThrow(() -> new CustomException(UserOperateEnum.USER_NAME_NOT_EXIST, name));
    }

    /**
     * 添加用户
     *
     * @param userInfo 用户信息
     * @return User 用户
     */
    @Override
    public User insertUser(User userInfo) {
        User user = userMapper.selectUserByName(userInfo.getName());
        if (user != null) {
            throw new CustomException(UserOperateEnum.USER_ALREADY_EXIST, userInfo.getName());
        }
        if (userMapper.insertUser(userInfo) == 0) {
            throw new CustomException(UserOperateEnum.USER_INSERT_FAIL);
        }
        return selectUserByName(userInfo.getName());
    }

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return true-成功 false-失败
     */
    @Override
    public User deleteUserById(Long id) {
        User user = selectUserById(id);
        if (userMapper.deleteUserById(id) == 0) {
            throw new CustomException(UserOperateEnum.USER_DELETE_FAIL);
        }
        return user;
    }

    /**
     * 修改用户信息
     * 成功则返回修改后的信息，失败返回修改前的信息
     *
     * @param userInfo 用户
     * @param id 用户id
     * @return User 用户
     */
    @Override
    public User updateUser(User userInfo, Long id) {
        selectUserById(id);
        if (userMapper.updateUser(userInfo) == 0) {
            throw new CustomException(UserOperateEnum.USER_UPDATE_FAIL);
        }
        return selectUserById(id);
    }
}
```

### UserController.class

```java
package com.xybert.springbootmybatis.controller;

import com.sun.istack.internal.NotNull;
import com.xybert.springbootmybatis.entity.User;
import com.xybert.springbootmybatis.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description 用户增删改查
 * @author xybert
 * @date 2022/10/18 14:40
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list")
    public List<User> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/info")
    public User getUserInfo(@NotNull Long id) {
        return userService.selectUserById(id);
    }

    @PostMapping("/insert")
    public User insertUser(@RequestBody @Validated User user) {
        return userService.insertUser(user);
    }

    @DeleteMapping("/delete")
    public User deleteUser(@NotNull Long id) {
        return userService.deleteUserById(id);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody @Validated User user, @NotNull Long id) {
        return userService.updateUser(user, id);
    }
}
```

### ErrorCodeBase.class

```java
package com.xybert.springbootmybatis.exception;

/**
 * @author xybert
 * @date 2022/10/19 14:43
 */

public interface ErrorCodeBase {

    /**
     * 获取错误码
     *
     * @return String
     */
    String getCode();

    /**
     * 获取中文错误信息
     *
     * @return String
     */
    String getCnMsg();

    /**
     * 获取英文错误信息
     *
     * @return String
     */
    String getEnMsg();

    /**
     * 格式化错误信息
     *
     * @param msg 错误信息
     * @param args args
     * @return String
     */
    default String format(String msg, Object... args) {
        return String.format(msg, args);
    }
}
```

### CustomException.class

```java
package com.xybert.springbootmybatis.exception;

import com.google.common.base.Objects;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @description 自定义异常类
 * @author xybert
 * @date 2022/10/19 14:26
 */

@Getter
@Setter
public class CustomException extends RuntimeException implements ErrorCodeBase {

    private String code;
    private String cnMsg;
    private String enMsg;

    public CustomException() {
    }

    public CustomException(ErrorCodeBase errorCode, Object... args) {
        super(String.format(errorCode.getCnMsg(), args));
        this.code = errorCode.getCode();
        this.cnMsg = String.format(errorCode.getCnMsg(), args);
        this.enMsg = String.format(errorCode.getEnMsg(), args);
    }

    public CustomException(ErrorCodeBase errorCode, Throwable cause, Object... args) {
        super(String.format(errorCode.getCnMsg(), args), cause);
        this.code = errorCode.getCode();
        this.cnMsg = String.format(errorCode.getCnMsg(), args);
        this.enMsg = String.format(errorCode.getEnMsg(), args);
    }

    public CustomException(String message, Throwable cause, Object... args) {
        super(String.format(message, args), cause);
    }

    public CustomException(String code, String cnMsg, String enMsg, Object... args) {
        super(String.format(cnMsg, args));
        this.code = code;
        this.cnMsg = StringUtils.isBlank(cnMsg) ? "" : String.format(cnMsg, args);
        this.enMsg = StringUtils.isBlank(enMsg) ? "" : String.format(enMsg, args);
    }

    public CustomException(String code, String cnMsg, String enMsg, Throwable cause, Object... args) {
        super(String.format(cnMsg, args), cause);
        this.code = code;
        this.cnMsg = StringUtils.isBlank(cnMsg) ? "" : String.format(cnMsg, args);
        this.enMsg = StringUtils.isBlank(enMsg) ? "" : String.format(enMsg, args);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CustomException)) {
            return false;
        }
        CustomException exception = (CustomException) obj;
        return Objects.equal(this.getCode(), exception.getCode()) &&
                Objects.equal(this.getCnMsg(), exception.getCnMsg()) &&
                Objects.equal(this.getEnMsg(), exception.getEnMsg());
    }

    @Override
    public int hashCode() {
        int result = 1;
        Object errorCode = this.getCode();
        result = result * 59 + (errorCode == null ? 43 : errorCode.hashCode());
        Object errorCnMsg = this.getCnMsg();
        result = result * 59 + (errorCnMsg == null ? 43 : errorCnMsg.hashCode());
        Object errorEnMsg = this.getEnMsg();
        result = result * 59 + (errorEnMsg == null ? 43 : errorEnMsg.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Exception(code=" + this.getCode() + ", cnMsg=" + this.getCnMsg() + ", enMsg=" + this.getEnMsg() + ")";
    }
}
```

### UserOperateEnum.class

```java
package com.xybert.springbootmybatis.enums;

import com.xybert.springbootmybatis.exception.ErrorCodeBase;
import lombok.Getter;

/**
 * @description 用户操作失败状态码
 * @author xybert
 * @date  2022/10/19 16:06
 */

@Getter
public enum UserOperateEnum implements ErrorCodeBase {
    /**
     * 用户操作状态码
     */
    USER_NOT_EXIST("10400", "用户不存在", "user doesn't exist."),
    USER_NAME_NOT_EXIST("10401", "用户【%s】不存在", "user %s doesn't exist."),
    USER_ALREADY_EXIST("10500", "用户【%s】已存在", "user %s already exist"),
    USER_INSERT_FAIL("10600", "用户添加失败", "failed to insert user"),
    USER_DELETE_FAIL("10700", "用户删除失败", "failed to delete user"),
    USER_UPDATE_FAIL("10800", "用户更新失败", "failed to update user"),
    ;

    final String code;
    final String cnMsg;
    final String enMsg;

    UserOperateEnum(String code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }
}
```