package com.video.live.dao;

import com.video.live.common.base.BaseDao;
import com.video.live.entity.Permission;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 11:41
 */
public interface PermissionDao extends BaseDao<Permission, Long> {

    /**
     * 查询是否存在该权限
     * @param allowUri
     * @return
     */
    Permission findByAllowUri(String allowUri);
}
