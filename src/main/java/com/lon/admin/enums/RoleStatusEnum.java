package com.lon.admin.enums;

import lombok.Getter;

/**
 * 角色状态枚举
 */
@Getter
public enum RoleStatusEnum {

    /**
     * 启用
     */
    ENABLE(0, "启用"),

    /**
     * 禁用
     */
    DISABLE(1, "禁用");

    private final int value;

    private final String desc;

    RoleStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}