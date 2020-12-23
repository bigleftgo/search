package com.hwkj.search.bean;

import com.hwkj.search.utils.DateUtil;
import lombok.Data;

import java.util.Date;

/**
 * com.hwkj.search.bean
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:35
 */
@Data
public class BaseBean {
    /**
     * 行更改人
     */
    private String row_changed_by;
    /**
     * 行改日期
     */
    private String row_changed_date;
    /**
     * 行创建人
     */
    private String row_created_by;
    /**
     * 行创建日期
     */
    private String row_created_date = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
    /**
     * 行生效日期（插入当前日期）
     */
    private String row_effective_date = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
    /**
     * 行失效日期
     */
    private String row_expiry_date;
    /**
     * 行质量
     */
    private String row_quality;
    /**
     * 是否有效
     */
    private String active_ind = "Y";
    /**
     * 行状态☆
     */
    private String rowState;
    /**
     * 备注
     */
    private String remark;
    /**
     * 生效日期
     */
    private String effective_date = DateUtil.parseDateToStr(new Date(), "yyyy-MM-dd");
    /**
     * 失效日期
     */
    private String expiry_date;
    /**
     * 全局ID
     */
    private String ppdm_guid;
    /**
     * 来源
     */
    private String source;
}
