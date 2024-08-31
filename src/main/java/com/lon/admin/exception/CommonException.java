package com.lon.admin.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class CommonException extends RuntimeException {

    private final String message;

    private final int code;

    public CommonException(String message) {
        this.message = message;
        this.code = -1;
    }

    public CommonException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public static CommonException fail(String message) {
        return new CommonException(message);
    }

    public static CommonException fail(String message, Integer code) {
        return new CommonException(message, code);
    }
}