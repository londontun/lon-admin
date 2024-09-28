package com.lon.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lon.admin.model.dto.user.QueryUserRequest;
import com.lon.admin.model.dto.user.SaveUserRequest;
import com.lon.admin.model.dto.user.UpdateUserRequest;
import com.lon.admin.model.entity.User;
import com.lon.admin.model.vo.user.UserVo;

import java.util.List;

/**
 * 用户服务接口约束类
 */
public interface IUserService extends IService<User> {

    /**
     * 获取用户Vo
     * @param user 用户
     * @return 用户Vo
     */
    UserVo getUserVo(User user);

    /**
     * 获取用户Vo列表
     * @param userList 用户列表
     * @return 用户Vo列表
     */
    List<UserVo> getUserVo(List<User> userList);

    /**
     * 获取查询条件
     * @param request 查询条件
     * @return 查询条件
     */
    QueryWrapper<User> getQueryWrapper(QueryUserRequest request);

    /**
     * 查询用户列表
     * @param request 查询条件
     * @return 用户列表
     */
    Page<UserVo> queryUserList(QueryUserRequest request);

    /**
     * 查询用户
     * @param userId 用户id
     * @return 用户Vo
     */
    UserVo queryUser(Long userId);

    /**
     * 更新用户
     * @param request 更新条件
     */
    void updateUser(UpdateUserRequest request);

    /**
     * 保存用户
     * @param request 保存条件
     */
    void saveUser(SaveUserRequest request);

    /**
     * 删除用户
     * @param userId 用户id
     */
    void deleteUser(Long userId);

    /**
     * 判断用户是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean isExist(String username);

    /**
     * 根据用户名查询用户
     */
    User loadUserByUsername(String username);
}
