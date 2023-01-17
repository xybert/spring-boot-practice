package com.xybert.springbootjpa.entity;

import com.xybert.springbootjpa.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author xybert
 * @description User实体类
 * @date 2022/12/28 17:29
 */

@Data
@Entity
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Table(name = "user")
@DynamicInsert
@DynamicUpdate
@SQLDelete(sql = "update user set deleted = 1 where id = ?")
@Where(clause = "deleted = 0")
public class User extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 2675898444394412622L;

    @Column(name = "account", length = 64, nullable = false, unique = true)
    private String account;

    @Column(name = "password", length = 128, nullable = false)
    private String password;

    @Column(name = "salt", length = 32, nullable = false)
    private String salt;

    @Column(name = "sex")
    private Integer sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "tel", length = 32)
    private String tel;

    @Column(name = "email", length = 64)
    private String email;

    @Column(name = "status")
    private Integer status;

    @Column(name = "deleted", columnDefinition = "tinyint default 0")
    private Integer deleted;

    @PreRemove
    public void deleteUser() {
        this.deleted = 1;
    }
}
