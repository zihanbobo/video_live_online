package com.video.live.web.service.impl;

import com.video.live.common.base.BaseDao;
import com.video.live.dao.UserDao;
import com.video.live.entity.User;
import com.video.live.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public BaseDao<User, Long> getRepository() {
        return this.userDao;
    }
}
