-- 清除存储过程
DROP PROCEDURE IF EXISTS user_add;

-- 定义新的分隔符
DELIMITER //
-- 创建存储过程
CREATE PROCEDURE user_add()
BEGIN
    IF NOT EXISTS(SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE table_schema = DATABASE()
                AND table_name = 'user'
                AND column_name = 'password')
    THEN
        ALTER TABLE user
            ADD COLUMN password VARCHAR(128) NOT NULL COMMENT '密码' AFTER account;
    END IF;

    IF NOT EXISTS(SELECT 1
                  FROM INFORMATION_SCHEMA.COLUMNS
                  WHERE table_schema = DATABASE()
                    AND table_name = 'user'
                    AND column_name = 'salt')
    THEN
        ALTER TABLE user
            ADD COLUMN salt VARCHAR(32) NOT NULL COMMENT '盐值' AFTER password;
    END IF;
END //
-- 还原默认分隔符
DELIMITER ;

-- 调用存储过程
CALL user_add();

DROP PROCEDURE IF EXISTS user_add;