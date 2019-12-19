package com.video.live.dao;

import com.video.live.common.base.BaseDao;
import com.video.live.entity.Role;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 11:42
 */
public interface RoleDao extends BaseDao<Role, Long> {

    /**
     * 根据用户 id查询用户角色
     *
     * @param userId 用户id
     * @return 用户角色
     */
    @Query(value = "select  * from role r join user_role ur on r.id=ur.role_id where ur.user_id=?1", nativeQuery = true)
    Optional<Role> findByUserId(Long userId);
}
