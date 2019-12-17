package com.video.live.web.service.impl;

import com.video.live.common.base.BaseDao;
import com.video.live.common.exception.ExceptionSupport;
import com.video.live.dao.RoleDao;
import com.video.live.entity.Role;
import com.video.live.model.input.RoleInputDTO;
import com.video.live.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 16:05
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Role addRole(RoleInputDTO inputDTO) {
        Role role = new Role();
        role.setRoleName(inputDTO.getRoleName());
        return this.save(role).orElseThrow(ExceptionSupport.serverExceptionSupplier("角色信息保存失败"));
    }

    @Override
    public BaseDao<Role, Long> getRepository() {
        return this.roleDao;
    }
}
