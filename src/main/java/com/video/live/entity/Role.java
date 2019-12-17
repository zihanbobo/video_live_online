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
 * @Date: 2019/12/17 11:28
 */

@Table(name = "role")
@Entity
@Getter
@Setter
@ToString
public class Role extends BaseEntity {

    @Column(name = "role_name",length = 50)
    private String roleName;
}
