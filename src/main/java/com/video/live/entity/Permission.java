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
@Table(name = "permission")
@Entity
@Getter
@Setter
@ToString
public class Permission extends BaseEntity {

    @Column(name = "permission_name", length = 50)
    private String permission_name;

    @Column(name = "allow_uri", length = 50)
    private String allowUri;
}
