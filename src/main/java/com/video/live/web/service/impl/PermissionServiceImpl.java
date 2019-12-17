package com.video.live.web.service.impl;

import com.google.common.collect.Lists;
import com.video.live.common.base.BaseDao;
import com.video.live.common.exception.OperationNotAllowException;
import com.video.live.common.util.EntityConvert;
import com.video.live.dao.PermissionDao;
import com.video.live.entity.Permission;
import com.video.live.entity.Role;
import com.video.live.entity.RolePermission;
import com.video.live.model.input.PermissionInputDTO;
import com.video.live.model.input.RoleInputDTO;
import com.video.live.web.service.PermissionService;
import com.video.live.web.service.RolePermissionService;
import com.video.live.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.video.live.common.exception.ExceptionSupport.noSuchResourceExceptionSupplier;
import static com.video.live.common.exception.ExceptionSupport.serverExceptionSupplier;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:04
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public BaseDao<Permission, Long> getRepository() {
        return this.permissionDao;
    }

    @Override
    public void addPermission(PermissionInputDTO inputDTO) {
        Permission permission = EntityConvert.convert(inputDTO, Permission.class);
        this.save(permission);
    }

    @Override
    public void removePermission(Long id) {
        permissionDao.deleteById(id);
        //todo 删除权限角色表信息
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addRole(RoleInputDTO inputDTO) {
        List<Long> permissionId = inputDTO.getPermissionId();
        List<Permission> permissionList = this.findById(permissionId).orElseThrow(noSuchResourceExceptionSupplier("权限不存在"));
        if (permissionList.containsAll(permissionId)) {
            throw new OperationNotAllowException("存在未知的权限，操作不允许");
        }
        Role role = roleService.addRole(inputDTO);
        List<RolePermission> rolePermissionList = Lists.newArrayListWithExpectedSize(permissionList.size());
        for (Permission permission : permissionList) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(permission.getId());
            rolePermission.setRoleId(role.getId());
            rolePermissionList.add(rolePermission);
        }
        rolePermissionService.save(rolePermissionList).orElseThrow(serverExceptionSupplier("角色信息保存失败"));
    }
}
