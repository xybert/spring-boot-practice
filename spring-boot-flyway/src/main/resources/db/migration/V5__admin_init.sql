INSERT INTO user(account, sex, age, tel, email)
VALUES ("admin", 1, 18, "15756487936", "xybert@163.com");

INSERT INTO role(name, create_user)
VALUES ("超级管理员", 1);

INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);