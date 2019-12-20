package com.video.live.web.service;

import com.video.live.common.base.BaseService;
import com.video.live.entity.Permission;
import com.video.live.model.input.PermissionInputDTO;
import com.video.live.model.input.RoleInputDTO;

import java.util.List;

/**
 * 权限服务接口
 *
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:02
 */
public interface PermissionService extends BaseService<Permission, Long> {

    /**
     * 添加权限
     *
     * @param inputDTO 输入的权限信息
     */
    void addPermission(PermissionInputDTO inputDTO);

    /**
     * 删除权限
     *
     * @param id 权限id
     */
    void removePermission(Long id);

    /**
     * 添加橘色信息
     *
     * @param inputDTO 输入的角色信息
     */
    void addRole(RoleInputDTO inputDTO);
}
