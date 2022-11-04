-- 创建数据库 spring-boot-practice
CREATE DATABASE IF NOT EXISTS `spring-boot-practice` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `spring-boot-practice`;
-- 创建表 user
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        varchar(64) NOT NULL COMMENT '用户名',
    `sex`         tinyint     NOT NULL COMMENT '性别 1-男 0-女',
    `age`         int         NOT NULL COMMENT '年龄',
    `tel`         varchar(32)          DEFAULT '' COMMENT '联系电话',
    `email`       varchar(64)          DEFAULT '' COMMENT '邮箱',
    `status`      tinyint     NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `role`        tinyint     NOT NULL DEFAULT 3 COMMENT '角色 1-超级管理员 2-管理员 3-普通用户',
    `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    CONSTRAINT user_name_uindex
        UNIQUE (name),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '用户表';