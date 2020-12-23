package com.hwkj.search.bean;

import java.util.Date;
import lombok.Data;

@Data
public class WellActivityCause {
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
     * 原因类型
     */
    private String cause_type;

    /**
     * 描述
     */
    private String description;

    /**
     * 设备ID
     */
    private String equipment_id;

    /**
     * 周期序号
     */
    private Integer period_obs_no;

    /**
     * 备注
     */
    private String remark;

    /**
     * PPDM_GUID
     */
    private String ppdm_guid;

    /**
     * 有效标识
     */
    private String active_ind;

    /**
     * 生效日期
     */
    private String effective_date;

    /**
     * 失效日期
     */
    private String expiry_date;

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

