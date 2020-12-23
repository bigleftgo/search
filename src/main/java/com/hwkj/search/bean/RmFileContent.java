package com.hwkj.search.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class RmFileContent {
    /**
     * 文件ID
     */
    private String file_id;

    /**
     * 文件内容
     */
    private byte[] file_content;

    /**
     * 文件大小
     */
    private BigDecimal file_size;

    /**
     * 文件大小单位
     */
    private String file_size_uom;

    /**
     * 数字格式
     */
    private String digital_format;

    /**
     * 记录ID
     */
    private String information_item_id;

    /**
     * 记录子类型
     */
    private String info_item_subtype;

    /**
     * 实物ID
     */
    private String physical_item_id;

    /**
     * 应用系统ID
     */
    private String sw_application_id;

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

