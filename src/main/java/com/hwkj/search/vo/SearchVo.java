package com.hwkj.search.vo;

import lombok.Data;

/**
 * com.hwkj.search.vo
 * 返回前端的对象
 * @author Lenovo
 * @CreateTime 2020/9/19 15:56
 */
@Data
public class SearchVo {
    /**
     * 标题
     */
    private String title;
    /**
     * 设计人
     */
    private String sjr;
    /**
     * 高亮内容
     */
    private String desc;
}
