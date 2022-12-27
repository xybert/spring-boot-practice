-- 清除存储过程
DROP PROCEDURE IF EXISTS user_modify;

-- 定义新的分隔符
DELIMITER //
-- 创建存储过程
CREATE PROCEDURE user_modify()
BEGIN
    -- 判断是否存在字段
    IF EXISTS(SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE table_schema = DATABASE()
                AND table_name = 'user'
                AND column_name = 'tel')
    THEN
        -- 通知user表调整tel字段类型
        ALTER TABLE user
            MODIFY COLUMN tel BIGINT NULL COMMENT '联系电话';
    END IF;
END //
-- 还原默认分隔符
DELIMITER ;

-- 调用存储过程
CALL user_modify();

DROP PROCEDURE IF EXISTS user_modify;