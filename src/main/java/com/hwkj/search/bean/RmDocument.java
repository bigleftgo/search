package com.hwkj.search.bean;

import lombok.Data;

import java.util.Date;

/**
 * com.hwkj.search.bean
 * 文档信息实体类
 * @author Lenovo
 * @CreateTime 2020/9/29 9:33
 */
@Data
public class RmDocument {
    /**
     * 记录子类型
     */
    private String info_item_subtype;
    /**
     * 记录ID
     */
    private String information_item_id;
    /**
     * 文件状态
     */
    private String document_status;
    /**
     * 文件类型
     */
    private String document_type;
    /**
     * 文献来源ID
     */
    private String source_document_id;
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
    private Date effective_date;
    /**
     * 失效日期
     */
    private Date expiry_date;
    /**
     * 来源
     */
    private String source;
    /**
     * PPDM_GUID
     */
    private String ppdm_guid;
    /**
     * 行更改人
     */
    private String row_changed_by;
    /**
     * 更改日期
     */
    private Date row_changed_date;
    /**
     * 行创建人
     */
    private String row_created_by;
    /**
     * 行创建日期
     */
    private Date row_created_date;
    /**
     * 行有效日期
     */
    private Date row_effective_date;
    /**
     * 行失效日期
     */
    private Date row_expiry_date;
    /**
     * 行质量
     */
    private String row_quality;
}
