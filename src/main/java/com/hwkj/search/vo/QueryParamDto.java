package com.hwkj.search.vo;

import lombok.Data;

/**
 * com.hwkj.search.vo
 *
 * @author Lenovo
 * @CreateTime 2020/10/16 14:31
 */
@Data
public class QueryParamDto {
    /**
     * 措施类型
     */
    private String cslx;
    /**
     * 设计时间
     */
    private String sjsj;
    /**
     * 设计单位
     */
    private String sjdw;
    /**
     * 设计类型
     */
    private String sjlx;
    /**
     * 油藏类型
     */
    private String yzlx;
    /**
     * 驱动类型
     */
    private String qdlx;
    /**
     * 知识名称
     */
    private String zsmc;
    /**
     * 指知识标签
     */
    private String zsbq;
    /**
     * 开始时间
     */
    private String kssj;
    /**
     * 结束时间
     */
    private String jssj;
    /**
     * 设计参数
     */
    private String sjcs;
    /**
     * 排序
     */
    private Integer px;

}
