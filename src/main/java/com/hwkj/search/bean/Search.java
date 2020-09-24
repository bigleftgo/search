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
    /**
     * 拼接关键点（是拼接must还是should）
     */
    private String status;
    /**
     * 设计起始时间
     */
    private String startTime;
    /**
     * 设计结束世界
     */
    private String endTime;


}
