package com.hwkj.search.common;

import lombok.Getter;

/**
 * ResultCodeEnum
 *
 * @author 夏峥嵘
 * 2020/7/28 15:47
 **/
@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    DELETE("delete"),
    UNKNOWN_REASON(2001, "未知错误");
    private Integer code;

    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    ResultCodeEnum(String message){
        this.message = message;
    }
}
