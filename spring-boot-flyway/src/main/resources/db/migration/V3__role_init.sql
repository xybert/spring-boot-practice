USE `spring-boot-practice`;
-- 创建表 role
CREATE TABLE IF NOT EXISTS `role`
(
    `id`             bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name`           varchar(64) NOT NULL COMMENT '角色名称',
    `create_user_id` bigint      NOT NULL COMMENT '创建人',
    `update_user_id` bigint      NOT NULL COMMENT '修改人',
    `create_time`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    CONSTRAINT user_name_uindex
        UNIQUE (name),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '角色表';