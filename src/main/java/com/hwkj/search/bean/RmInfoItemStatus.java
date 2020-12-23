package com.hwkj.search.bean;

import java.util.Date;
import lombok.Data;

@Data
public class RmInfoItemStatus {
    /**
     * 记录子类型
     */
    private String info_item_subtype;

    /**
     * 记录ID
     */
    private String information_item_id;

    /**
     * 状态ID
     */
    private String status_id;

    /**
     * 状态
     */
    private String status;

    /**
     * 状态日期
     */
    private String status_date;

    /**
     * 状态类型
     */
    private String status_type;

    /**
     * 备注
     */
    private String remark;

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
     * PPDM_GUID
     */
    private String ppdm_guid;

    /**
     * 来源
     */
    private String source;

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

