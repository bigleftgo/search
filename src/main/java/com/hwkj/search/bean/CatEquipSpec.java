package com.hwkj.search.bean;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class CatEquipSpec {
    /**
     * 设备目录ID
     */
    private String catalogue_equip_id;

    /**
     * 规格ID
     */
    private String spec_id;

    /**
     * 规格代码
     */
    private String spec_code;

    /**
     * 规格描述
     */
    private String spec_desc;

    /**
     * 规格类型
     */
    private String spec_type;

    /**
     * 平均值
     */
    private BigDecimal average_value;

    /**
     * 平均值原始单位
     */
    private String average_value_ouom;

    /**
     * 平均值单位
     */
    private String average_value_uom;

    /**
     * 日期格式
     */
    private String date_format_desc;

    /**
     * 最大日期
     */
    private Date max_date;

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
    private Date min_date;

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
     * 备注
     */
    private String remark;

    /**
     * 来源
     */
    private String source;

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
    private Date effective_date;

    /**
     * 失效日期
     */
    private Date expiry_date;

    /**
     * 行更改人
     */
    private String row_changed_by;

    /**
     * 行更改日期
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

