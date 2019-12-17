package com.video.live.web.service;

import com.video.live.common.base.BaseService;
import com.video.live.entity.Role;
import com.video.live.model.input.RoleInputDTO;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 15:35
 */
public interface RoleService extends BaseService<Role, Long> {

    /**
     * 添加角色
     *
     * @param inputDTO 输入的角色信息
     */
    Role addRole(RoleInputDTO inputDTO);
}
