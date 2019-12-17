package com.video.live.dao;

import com.video.live.common.base.BaseDao;
import com.video.live.entity.User;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/13 16:27
 */
public interface UserDao extends BaseDao<User,Long> {

    long countByUserName(String userName);
}
