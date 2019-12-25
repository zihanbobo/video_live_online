package com.video.live.dao;

import com.video.live.common.base.BaseDao;
import com.video.live.entity.User;

import java.util.Optional;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 16:27
 */
public interface UserDao extends BaseDao<User, Long> {

    Long countByUserName(String userName);

    /**
     * 根据用户名查询用户信息
     *
     * @param userName 用户名
     * @return 用户信息
     */
    Optional<User> findByUserName(String userName);

}
