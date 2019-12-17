package com.video.live.entity;

import com.video.live.common.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 11:36
 */
@Table(name = "user_role")
@Entity
@Getter
@Setter
@ToString
public class UserRole extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "role_id")
    private Long roleId;
}
