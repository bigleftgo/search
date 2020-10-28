package com.hwkj.search.bean;

import lombok.Data;

import java.util.List;

/**
 * com.hwkj.search.bean
 * 更新知识父级
 * @author Lenovo
 * @CreateTime 2020/10/28 15:21
 */
@Data
public class ProUpKonwledge {
    /**
     * 更新知识类
     */
    private List<UpKonwledge> list;
    /**
     * 文档id
     */
    private String id;
    /**
     * 文件路径
     */
    private String path;
}
