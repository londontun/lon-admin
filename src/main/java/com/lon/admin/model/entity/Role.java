package com.lon.admin.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 */
@Data
@TableName("t_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 状态  0：启用   1：禁用
     */
    private Integer status;

    /**
     * 角色描述
     */
    private String roleDesc;
}

