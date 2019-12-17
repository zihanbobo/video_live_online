package com.video.live.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.video.live.common.base.BaseDao;
import com.video.live.common.exception.ExceptionSupport;
import com.video.live.common.exception.OperationNotAllowException;
import com.video.live.common.util.EntityConvert;
import com.video.live.dao.UserDao;
import com.video.live.entity.User;
import com.video.live.model.input.UserInputDTO;
import com.video.live.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public void addUser(UserInputDTO userInputDTO) {
        User user = EntityConvert.convert(userInputDTO, User.class);
        boolean exited = userDao.countByUserName(user.getUserName()) > 0;
        if (exited){
            throw new OperationNotAllowException(StrUtil.format("用户[{}]已存在",user.getUserName()));
        }
        User save = this.save(user).orElseThrow(ExceptionSupport.serverExceptionSupplier("用户信息保存失败"));
    }
}
