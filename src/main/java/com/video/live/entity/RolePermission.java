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
 * @Date: 2019/12/17 11:34
 */
@Table(name = "role_permission")
@Entity
@Getter
@Setter
@ToString
public class RolePermission extends BaseEntity {

    @Column(name = "permission_id")
    private Long permissionId;

    @Column(name = "role_id")
    private Long roleId;
}
