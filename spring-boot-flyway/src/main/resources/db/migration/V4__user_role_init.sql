USE `spring-boot-practice`;
-- 创建表 user_role
CREATE TABLE IF NOT EXISTS `user_role`
(
    `user_id` BIGINT NOT NULL COMMENT '用户id',
    `role_id` BIGINT NOT NULL COMMENT '角色id',
    FOREIGN KEY (`user_id`) REFERENCES user (`id`),
    FOREIGN KEY (`role_id`) REFERENCES role (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '用户角色表';