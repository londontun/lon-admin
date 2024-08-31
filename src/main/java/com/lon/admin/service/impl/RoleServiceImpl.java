package com.lon.admin.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lon.admin.enums.RoleStatusEnum;
import com.lon.admin.exception.CommonException;
import com.lon.admin.mapper.RoleMapper;
import com.lon.admin.model.dto.role.QueryRoleRequest;
import com.lon.admin.model.dto.role.SaveRoleRequest;
import com.lon.admin.model.dto.role.UpdateRoleRequest;
import com.lon.admin.model.entity.Role;
import com.lon.admin.model.vo.role.RoleVo;
import com.lon.admin.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色服务接口实现类
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public RoleVo getRoleVo(Role role) {
        if (Objects.isNull(role)) {
            return null;
        }
        RoleVo roleVO = new RoleVo();
        BeanUtils.copyProperties(role, roleVO);
        return roleVO;
    }

    @Override
    public List<RoleVo> getRoleVo(List<Role> roleList) {
        if (CollUtil.isEmpty(roleList)) {
            return new ArrayList<>();
        }
        return roleList.stream().map(this::getRoleVo).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<Role> getQueryWrapper(QueryRoleRequest request) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        // 精确查询
        wrapper.eq(Objects.nonNull(request.getStatus()), "status", request.getStatus());
        // 模糊查询
        wrapper.like(StringUtils.isNotBlank(request.getRoleName()), "role_name", request.getRoleName());
        return wrapper;
    }

    @Override
    public Page<RoleVo> queryRoleList(QueryRoleRequest request) {
        long current = request.getPageNum();
        long size = request.getPageSize();
        Page<Role> rolePage = page(new Page<>(current, size), getQueryWrapper(request));
        Page<RoleVo> roleVoPage = new Page<>(current, size, rolePage.getTotal());
        List<RoleVo> roleVO = getRoleVo(rolePage.getRecords());
        roleVoPage.setRecords(roleVO);
        return roleVoPage;
    }

    @Override
    public RoleVo queryRole(Long roleId) {
        Role role = getById(roleId);
        if (Objects.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        return getRoleVo(role);
    }

    @Override
    public void updateRole(UpdateRoleRequest request) {
        Role role = getById(request.getId());
        if (Objects.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        BeanUtils.copyProperties(request, role);
        updateById(role);
    }

    @Override
    public void saveRole(SaveRoleRequest request) {
        if (isExist(request.getRoleCode())) {
            throw CommonException.fail("角色编码已存在");
        }
        Role role = new Role();
        BeanUtils.copyProperties(request, role);
        save(role);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = getById(roleId);
        if (Objects.isNull(role)) {
            throw CommonException.fail("角色不存在");
        }
        role.setStatus(RoleStatusEnum.DISABLE.getValue());
        updateById(role);
    }

    @Override
    public boolean isExist(String rolename) {
        return query().eq("role_name", rolename).exists();
    }
}
