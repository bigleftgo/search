package com.hwkj.search.bean;

import java.util.Date;
import lombok.Data;

@Data
public class RmCreator {
    /**
     * 记录子类型
     */
    private String info_item_subtype;

    /**
     * 记录ID
     */
    private String information_item_id;

    /**
     * 创建者ID
     */
    private String creator_id;

    /**
     * 创建对象ID
     */
    private String creator_ba_id;

    /**
     * 对象类型
     */
    private String creator_ba_type;

    /**
     * 创建者名字
     */
    private String creator_name;

    /**
     * 创建者类型
     */
    private String creator_type;

    /**
     * 第一个名字
     */
    private String first_name;

    /**
     * 最后一个名字
     */
    private String last_name;

    /**
     * 中间名字
     */
    private String middle_name;

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

