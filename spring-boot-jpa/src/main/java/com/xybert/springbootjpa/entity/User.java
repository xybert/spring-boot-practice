package com.xybert.springbootjpa.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author xybert
 * @description User实体类
 * @date 2022/12/28 17:29
 */

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
