package com.lon.admin.enums;

import lombok.Getter;

/**
 * 用户状态枚举
 */
@Getter
public enum UserStatusEnum {

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

    UserStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}