package com.video.live.entity;

import com.video.live.common.base.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * 用户表
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 15:23
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
public class User extends BaseEntity {

    @Column(name = "username", length = 50)
    private String userName;

    @Column(name = "password", length = 64)
    private String password;

    @Column(name = "sex", length = 5)
    private String sex;

    @Column(name = "age")
    private Integer age;

    @Column(name = "phone", length = 11)
    private String phone;

    @Transient
    private List<Role> roles;

    @Transient
    private List<Permission> permissions;
}
