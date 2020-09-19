package com.hwkj.search.bean;

import lombok.Data;

/**
 * com.hwkj.search.bean
 *
 * @author Lenovo
 * @CreateTime 2020/9/19 9:43
 */
@Data
public class Search {
    /**
     * 指定对哪个域进行查询
     */
    private String key;
    /**
     * 所要查询的值
     */
    private String value;
}
