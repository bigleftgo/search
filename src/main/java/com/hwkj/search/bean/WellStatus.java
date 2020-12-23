package com.hwkj.search.bean;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class WellStatus {
    /**
     * 井ID
     */
    private String uwi;

    /**
     * 来源
     */
    private String source;

    /**
     * 状态ID
     */
    private String status_id;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 结束时间
     */
    private String end_time;

    /**
     * 状态
     */
    private String status;

    /**
     * 状态日期
     */
    private String status_date;

    /**
     * 状态深度
     */
    private BigDecimal status_depth;

    /**
     * 状态深度原始单位
     */
    private String status_depth_ouom;

    /**
     * 状态限定符
     */
    private String status_qualifier;

    /**
     * 状态限定值
     */
    private String status_qualifier_value;

    /**
     * 状态类型
     */
    private String status_type;

    /**
     * 性能百分比
     */
    private BigDecimal percent_capability;

    /**
     * 时区
     */
    private String timezone;

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

