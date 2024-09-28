package com.lon.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lon.admin.enums.UserStatusEnum;
import com.lon.admin.exception.CommonException;
import com.lon.admin.mapper.UserMapper;
import com.lon.admin.model.dto.user.QueryUserRequest;
import com.lon.admin.model.dto.user.SaveUserRequest;
import com.lon.admin.model.dto.user.UpdateUserRequest;
import com.lon.admin.model.entity.User;
import com.lon.admin.model.vo.user.UserVo;
import com.lon.admin.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 用户服务接口实现类
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public UserVo getUserVo(User user) {
        if (Objects.isNull(user)) {
            return null;
        }
        UserVo userVO = new UserVo();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVo> getUserVo(List<User> userList) {
        if (CollUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVo).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(QueryUserRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        // 精确查询
        wrapper.eq(Objects.nonNull(request.getStatus()), "status", request.getStatus());
        // 模糊查询
        wrapper.like(StringUtils.isNotBlank(request.getNickName()), "nick_name", request.getNickName());
        wrapper.like(StringUtils.isNotBlank(request.getMobile()), "mobile", request.getMobile());
        wrapper.like(StringUtils.isNotBlank(request.getEmail()), "email", request.getEmail());
        return wrapper;
    }

    @Override
    public Page<UserVo> queryUserList(QueryUserRequest request) {
        long current = request.getCurrent();
        long size = request.getSize();
        Page<User> userPage = page(new Page<>(current, size), getQueryWrapper(request));
        Page<UserVo> userVoPage = new Page<>(current, size, userPage.getTotal());
        List<UserVo> userVO = getUserVo(userPage.getRecords());
        userVoPage.setRecords(userVO);
        return userVoPage;
    }

    @Override
    public UserVo queryUser(Long userId) {
        User user = getById(userId);
        if (Objects.isNull(user)) {
            throw CommonException.fail("用户不存在");
        }
        return getUserVo(user);
    }

    @Override
    public void updateUser(UpdateUserRequest request) {
        User user = getById(request.getId());
        if (Objects.isNull(user)) {
            throw CommonException.fail("用户不存在");
        }
        BeanUtils.copyProperties(request, user);
        updateById(user);
    }

    @Override
    public void saveUser(SaveUserRequest request) {
        if (isExist(request.getUsername())) {
            throw CommonException.fail("用户名已存在");
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        // 创建 BCryptPasswordEncoder 实例
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodePwd = passwordEncoder.encode(request.getPassword());
        user.setPassword(encodePwd);
        save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        User user = getById(userId);
        if (Objects.isNull(user)) {
            throw CommonException.fail("用户不存在");
        }
        user.setStatus(UserStatusEnum.DISABLE.getValue());
        updateById(user);
    }

    @Override
    public boolean isExist(String username) {
        return query().eq("username", username).exists();
    }

    @Override
    public User loadUserByUsername(String username) {
        return query().eq("username", username).one();
    }
}
