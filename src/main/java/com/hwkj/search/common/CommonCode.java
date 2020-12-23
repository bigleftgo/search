package com.hwkj.search.common;

/**
 * com.hwkj.search.common
 * 通用枚举值
 *
 * @author Lenovo
 * @CreateTime 2020/12/18 11:57
 */
public enum CommonCode {
    SOURCE("知识参数"),
    INFO_ITEM_SUBTYPE("RM_COMPOSITE"),
    INFO_ITEM_SUBTYPE_DOC("RM_DOCUMENT"),
    CASING_SIZE("套管尺寸"),
    CAUSE_TYPE("上修原因");
    private final String description;

    CommonCode(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }
}
