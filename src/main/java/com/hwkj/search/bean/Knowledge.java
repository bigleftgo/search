package com.hwkj.search.bean;

import lombok.Data;

import java.util.List;

/**
 * com.hwkj.search.bean
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 15:00
 */
@Data
public class Knowledge {
    /**
     * id
     */
    private List<Integer> ids;
    /**
     * 各个字段名
     */
    private List<String> names;
    /**
     * 各个字段值
     */
    private List<String> values;
    /**
     * 文档地址
     */
    private List<String> path;
}
