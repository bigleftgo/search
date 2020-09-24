package com.hwkj.search.common;

/**
 * 错误代码
 */
public enum ErrorCode {
    NO_ERROR(0,"成功"),
    INVALID_PARAMETER(3, "参数错误"),
    SYSTEM_ERROR(6, "系统错误"),
    FILE_UPLOAD(12, "文件上传错误"),
    FILE_ERROR(14, "文件错误"),
    FILE_MISS_CHUNKS(16, "文件部分模块上传错误"),
    INDEX_FAILURE(15,"索引创建失败");

    private final Integer code;

    private final String description;

    ErrorCode(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
