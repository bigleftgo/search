package com.hwkj.search.bean;

import lombok.Data;

/**
 * com.hwkj.search.bean
 *
 * @author Lenovo
 * @CreateTime 2020/9/28 15:31
 */
@Data
public class QueryParam {
    private String zzmc;
    private String cslx;
    private String sjdw;
    private String qdlx;
    private String yclx;
    private String startTime;
    private String endTime;
    private Integer sortName;
    private Integer sortTime;
    private Integer sortType;
}
