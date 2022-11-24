USE `spring-boot-practice`;
-- 创建表 role
CREATE TABLE IF NOT EXISTS `student`
(
    `id`          bigint      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `stuNo`       bigint      NOT NULL COMMENT '学号',
    `clazz`       varchar(64) NOT NULL COMMENT '班级',
    `name`        varchar(64) NOT NULL COMMENT '姓名',
    `sex`         tinyint     NOT NULL COMMENT '性别 1-男 0-女',
    `age`         int         NOT NULL COMMENT '年龄',
    `tel`         varchar(32) NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`       varchar(64)          DEFAULT '' COMMENT '邮箱',
    `guardian`    varchar(64) NOT NULL DEFAULT '' COMMENT '监护人',
    `chinese`     FLOAT                DEFAULT 0 COMMENT '语文成绩',
    `math`        FLOAT                DEFAULT 0 COMMENT '数学成绩',
    `english`     FLOAT                DEFAULT 0 COMMENT '英语成绩',
    `physics`     FLOAT                DEFAULT 0 COMMENT '物理成绩',
    `chemistry`   FLOAT                DEFAULT 0 COMMENT '化学成绩',
    `biology`     FLOAT                DEFAULT 0 COMMENT '生物成绩',
    `score`       FLOAT                DEFAULT 0 COMMENT '总分',
    `clazz_rank`  int COMMENT '班级排名',
    `grade_rank`  int COMMENT '年级排名',
    `create_time` DATETIME    NOT NULL DEFAULT NOW() COMMENT '创建时间',
    `update_time` DATETIME    NOT NULL DEFAULT NOW() ON UPDATE NOW() COMMENT '修改时间',
    CONSTRAINT stuNo_uindex
        UNIQUE (stuNo),
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
    COMMENT '学生表';