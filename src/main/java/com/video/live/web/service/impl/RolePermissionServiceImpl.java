package com.video.live.web.service.impl;

import com.video.live.common.base.BaseDao;
import com.video.live.dao.RolePermissionDao;
import com.video.live.entity.RolePermission;
import com.video.live.web.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 16:11
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public BaseDao<RolePermission, Long> getRepository() {
        return this.rolePermissionDao;
    }
}
