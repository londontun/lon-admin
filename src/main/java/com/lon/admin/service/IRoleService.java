package com.lon.admin.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lon.admin.model.dto.role.QueryRoleRequest;
import com.lon.admin.model.dto.role.SaveRoleRequest;
import com.lon.admin.model.dto.role.UpdateRoleRequest;
import com.lon.admin.model.entity.Role;
import com.lon.admin.model.vo.role.RoleVo;

import java.util.List;

/**
 * 角色服务接口约束类
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取角色Vo
     * @param role 角色
     * @return 角色Vo
     */
    RoleVo getRoleVo(Role role);

    /**
     * 获取角色Vo列表
     * @param userList 角色列表
     * @return 角色Vo列表
     */
    List<RoleVo> getRoleVo(List<Role> userList);

    /**
     * 获取查询条件
     * @param request 查询条件
     * @return 查询条件
     */
    QueryWrapper<Role> getQueryWrapper(QueryRoleRequest request);

    /**
     * 查询角色列表
     * @param request 查询条件
     * @return 角色列表
     */
    Page<RoleVo> queryRoleList(QueryRoleRequest request);

    /**
     * 查询角色
     * @param roleId 角色id
     * @return 角色Vo
     */
    RoleVo queryRole(Long roleId);

    /**
     * 更新角色
     * @param request 更新条件
     */
    void updateRole(UpdateRoleRequest request);

    /**
     * 保存角色
     * @param request 保存条件
     */
    void saveRole(SaveRoleRequest request);

    /**
     * 删除角色
     * @param roleId 角色id
     */
    void deleteRole(Long roleId);

    /**
     * 判断角色是否存在
     * @param roleCode 角色编码
     * @return 是否存在
     */
    boolean isExist(String roleCode);
}
