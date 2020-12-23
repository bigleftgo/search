package com.hwkj.search.bean;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class WellMiscData {
    /**
     * 井ID
     */
    private String uwi;

    /**
     * 来源
     */
    private String source;

    /**
     * 其他数据类型
     */
    private String misc_data_type;

    /**
     * 其他数据序号
     */
    private Integer misc_data_obs_no;

    /**
     * 其他数据代码
     */
    private String misc_data_code;

    /**
     * 其他数据说明
     */
    private String misc_data_desc;

    /**
     * 成本
     */
    private BigDecimal cost;

    /**
     * 货币兑换
     */
    private BigDecimal currency_conversion;

    /**
     * 货币原始单位
     */
    private String currency_ouom;

    /**
     * 货币单位
     */
    private String currency_uom;

    /**
     * 数据值
     */
    private BigDecimal data_value;

    /**
     * 数据值原始单位
     */
    private String data_value_ouom;

    /**
     * 数据值单位
     */
    private String data_value_uom;

    /**
     * 日期格式
     */
    private String date_format_desc;

    /**
     * 最大日期
     */
    private String max_date;

    /**
     * 最大值
     */
    private BigDecimal max_value;

    /**
     * 最大值原始单位
     */
    private String max_value_ouom;

    /**
     * 最大值单位
     */
    private String max_value_uom;

    /**
     * 最小日期
     */
    private String min_date;

    /**
     * 最小值
     */
    private BigDecimal min_value;

    /**
     * 最小值原始单位
     */
    private String min_value_ouom;

    /**
     * 最小值单位
     */
    private String min_value_uom;

    /**
     * 参考值
     */
    private BigDecimal reference_value;

    /**
     * 参考值原始单位
     */
    private String reference_value_ouom;

    /**
     * 参考值类型
     */
    private String reference_value_type;

    /**
     * 参考值单位
     */
    private String reference_value_uom;

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

