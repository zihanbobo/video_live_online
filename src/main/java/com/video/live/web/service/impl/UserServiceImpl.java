package com.video.live.web.service.impl;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.video.live.common.base.BaseDao;
import com.video.live.common.exception.ExceptionSupport;
import com.video.live.common.exception.OperationNotAllowException;
import com.video.live.common.util.EntityConvert;
import com.video.live.dao.PermissionDao;
import com.video.live.dao.RoleDao;
import com.video.live.dao.UserDao;
import com.video.live.dao.UserRoleDao;
import com.video.live.entity.Permission;
import com.video.live.entity.Role;
import com.video.live.entity.User;
import com.video.live.entity.UserRole;
import com.video.live.model.input.UserInputDTO;
import com.video.live.web.service.UserService;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.video.live.common.constant.CacheConstant.USER_NAME_CACHE;


/**
 * @Author: Deng Yunhu
 * @Date: 2019/12/17 14:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public BaseDao<User, Long> getRepository() {
        return this.userDao;
    }

    @Override
    public void addUser(UserInputDTO userInputDTO) {
        boolean exited = userDao.countByUserName(userInputDTO.getUserName()) > 0;
        if (exited) {
            throw new OperationNotAllowException(StrUtil.format("用户[{}]已存在", userInputDTO.getUserName()));
        }
        User user = EntityConvert.convert(userInputDTO, User.class);
        User save = this.save(user).orElseThrow(ExceptionSupport.serverExceptionSupplier("用户信息保存失败"));
        UserRole userRole = new UserRole();
        userRole.setUserId(save.getId());
        userRole.setRoleId(userInputDTO.getRoleId());
        userRoleDao.save(userRole);
    }

    @Override
    @Cacheable(value = USER_NAME_CACHE, key = "#userName", unless = "#result==null")
    public Optional<User> findByUserName(String userName) {
        User user = userDao.findByUserName(userName).orElse(null);
        if (Objects.isNull(user)) {
            return Optional.empty();
        }
        Role role = roleDao.findByUserId(user.getId()).orElseGet(Role::new);
        List<Permission> permissions = permissionDao.findByRoleId(role.getId()).orElseGet(PersistentBag::new);
        user.setRoles(Lists.newArrayList(role));
        user.setPermissions(permissions);
        return Optional.of(user);
    }
}
