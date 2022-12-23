USE `spring-boot-practice`;
-- 创建表 role
CREATE TABLE IF NOT EXISTS `role`
(
    `id`          BIGINT      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`        VARCHAR(64) NOT NULL COMMENT '角色名称',
    `create_user` BIGINT      NOT NULL DEFAULT 1 COMMENT '创建人',
    `update_user` BIGINT      NOT NULL DEFAULT 1 COMMENT '修改人',
    `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '修改时间',
    `deleted`     TINYINT     NOT NULL DEFAULT 0 COMMENT '逻辑删除',
    CONSTRAINT role_name_uindex
        UNIQUE (name),
    PRIMARY KEY (`id`) USING BTREE,
    FOREIGN KEY (`create_user`) REFERENCES user(`id`),
    FOREIGN KEY (`update_user`) REFERENCES user(`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色表';