package com.hwkj.search.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * com.hwkj.search.bean
 * 任何知识信息
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 15:00
 */
@Data
public class Knowledge implements Serializable {
    /**
     * id
     */
    private List<String> ids;
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
    /**
     * 标签
     */
    private String zsbq;
}
