package com.video.live.web.service;

import com.video.live.common.base.BaseService;
import com.video.live.entity.Role;
import com.video.live.model.input.RoleInputDTO;
import org.springframework.data.jpa.repository.Query;

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

    /**
     * 根据用户id查询用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    @Query(value = "select r from role r join user_role ur on r.id=ur.role_id where ur.user_id=?!",nativeQuery = true)
    Role findByUserId(Long userId);
}
