package com.hwkj.search.bean;

import lombok.Data;

@Data
public class WellActProduce {
    /**
     * 井ID
     */
    private String uwi;

    /**
     * 来源
     */
    private String source;

    /**
     * 活动序号
     */
    private Integer activity_obs_no;

    /**
     * 项目阶段
     */
    private String pro_phase;

    /**
     * 周期类型
     */
    private String period_type;

    /**
     * 取值时间
     */
    private String taking_time;

    /**
     * 行更改人
     */
    private String row_changed_by;

    /**
     * 行更改日期
     */
    private String row_changed_date;

    /**
     * 行创建人
     */
    private String row_created_by;

    /**
     * 行创建日期
     */
    private String row_created_date;

    /**
     * 行有效日期
     */
    private String row_effective_date;

    /**
     * 行失效日期
     */
    private String row_expiry_date;

    /**
     * 行质量
     */
    private String row_quality;
}

