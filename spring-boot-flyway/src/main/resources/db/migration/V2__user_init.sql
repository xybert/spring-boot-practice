-- 创建数据库 spring-boot-practice
-- CREATE DATABASE IF NOT EXISTS `spring-boot-practice` DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

USE `spring-boot-practice`;
-- 创建表 user
CREATE TABLE IF NOT EXISTS `user`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account`     VARCHAR(64) NOT NULL COMMENT '用户名',
    `sex`         TINYINT COMMENT '性别 1-男 0-女',
    `age`         INT COMMENT '年龄',
    `tel`         VARCHAR(32)          DEFAULT '' COMMENT '联系电话',
    `email`       VARCHAR(64)          DEFAULT '' COMMENT '邮箱',
    `status`      TINYINT     NOT NULL DEFAULT 1 COMMENT '状态 1-启用 0-禁用',
    `create_user` BIGINT      NOT NULL DEFAULT 1 COMMENT '创建人',
    `update_user` BIGINT COMMENT '更新人',
    `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME ON UPDATE NOW() COMMENT '更新时间',
    `deleted`     TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    CONSTRAINT account_uindex
        UNIQUE (account),
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (`create_user`) REFERENCES user (`id`),
    FOREIGN KEY (`update_user`) REFERENCES user (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '用户表';