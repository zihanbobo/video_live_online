package com.video.live.dao;

import com.video.live.common.base.BaseDao;
import com.video.live.entity.Permission;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 11:41
 */
public interface PermissionDao extends BaseDao<Permission, Long> {

    /**
     * 查询是否存在该权限
     *
     * @param allowUri
     * @return
     */
    Permission findByAllowUri(String allowUri);

    /**
     * 根据角色id 查询用户权限
     *
     * @param roleId 角色id
     * @return 权限
     */
    @Query(value = "select * from permission p join role_permission rp on rp.permission_id=p.id where rp.role_id=?1", nativeQuery = true)
    Optional<List<Permission>> findByRoleId(Long roleId);
}
