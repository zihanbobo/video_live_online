package com.video.live.web.service;

/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 13:59
 */

import com.video.live.common.base.BaseService;
import com.video.live.entity.User;
import com.video.live.model.input.UserInputDTO;

import java.util.Optional;

/**
 * 用户服务接口
 */
public interface UserService extends BaseService<User, Long> {

    /**
     * 添加用户
     *
     * @param userInputDTO 输入的用户信息
     */
    void addUser(UserInputDTO userInputDTO);

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return 用户信息
     */
    Optional<User> findByUserName(String userName);
}
