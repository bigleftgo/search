package com.hwkj.search.bean;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WellActivity {
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
     * 有效标识
     */
    private String active_ind;

    /**
     * 活动持续时间
     */
    private BigDecimal activity_duration;

    /**
     * 活动持续时间单位
     */
    private String activity_duration_ouom;

    /**
     * 活动产物
     */
    private String activity_product;

    /**
     * 活动集ID
     */
    private String activity_set_id;

    /**
     * 井活动类型ID
     */
    private String activity_type_id;

    /**
     * 顶深
     */
    private BigDecimal top_depth;

    /**
     * 顶深单位
     */
    private String top_depth_ouom;

    /**
     * 顶部地层ID
     */
    private String top_strat_unit_id;

    /**
     * 底深
     */
    private BigDecimal base_depth;

    /**
     * 底深单位
     */
    private String base_depth_ouom;

    /**
     * 底界地层ID
     */
    private String base_strat_unit_id;

    /**
     * 井喷出流体
     */
    private String blowout_fluid;

    /**
     * 控制日期
     */
    private String control_date;

    /**
     * 控制深度
     */
    private BigDecimal control_depth;

    /**
     * 控制深度单位
     */
    private String control_depth_ouom;

    /**
     * 停工类型
     */
    private String downtime_type;

    /**
     * 生效日期
     */
    private String effective_date;

    /**
     * 开始日期
     */
    private String start_date;

    /**
     * 开始时间
     */
    private String start_time;

    /**
     * 开始时区
     */
    private String start_timezone;

    /**
     * 结束日期
     */
    private String end_date;

    /**
     * 结束时间
     */
    private String end_time;

    /**
     * 结束时区
     */
    private String end_timezone;

    /**
     * 事件日期
     */
    private String event_date;

    /**
     * 事件深度
     */
    private BigDecimal event_depth;

    /**
     * 事件深度单位
     */
    private String event_depth_ouom;

    /**
     * 失效日期
     */
    private String expiry_date;

    /**
     * 岩性
     */
    private String lithology;

    /**
     * 严重程度
     */
    private String lost_circ_severity;

    /**
     * 损失材料量
     */
    private BigDecimal lost_material_amount;

    /**
     * 损失材料量单位
     */
    private String lost_material_amount_ouom;

    /**
     * 损失材料类型
     */
    private String lost_material_type;

    /**
     * 损失量
     */
    private BigDecimal lost_volume;

    /**
     * 损失量单位
     */
    private String lost_volume_ouom;

    /**
     * 损失量产品
     */
    private String lost_volume_product;

    /**
     * 损失量单位
     */
    private String lost_volume_uom;

    /**
     * 周期序号
     */
    private Integer period_obs_no;

    /**
     * PPDM_GUID
     */
    private String ppdm_guid;

    /**
     * 生产管柱ID
     */
    private String prod_string_id;

    /**
     * 生产管柱来源
     */
    private String prod_string_source;

    /**
     * 生产管柱序号
     */
    private Integer pr_str_form_obs_no;

    /**
     * 备注
     */
    private String remark;

    /**
     * 报告代码
     */
    private String reported_code;

    /**
     * 报告时间
     */
    private BigDecimal reported_time_elapsed;

    /**
     * 报告时间单位
     */
    private String reported_time_elapsed_ouom;

    /**
     * 报告的真实垂直深度
     */
    private BigDecimal reported_tvd;

    /**
     * 报告的真实垂直深度单位
     */
    private String reported_tvd_ouom;

    /**
     * 开始泥浆密度
     */
    private BigDecimal start_mud_density;

    /**
     * 开始泥浆密度单位
     */
    private String start_mud_density_ouom;

    /**
     * 开始泥浆粘度
     */
    private BigDecimal start_mud_viscosity;

    /**
     * 开始泥浆粘度单位
     */
    private String start_mud_viscosity_ouom;

    /**
     * 最终泥浆密度
     */
    private BigDecimal final_mud_density;

    /**
     * 最终泥浆密度单位
     */
    private String final_mud_density_ouom;

    /**
     * 最终泥浆粘度
     */
    private BigDecimal final_mud_viscosity;

    /**
     * 最终泥浆粘度单位
     */
    private String final_mud_viscosity_ouom;

    /**
     * 地层名称集ID
     */
    private String strat_name_set_id;

    /**
     * 总时间
     */
    private BigDecimal total_time_elapsed;

    /**
     * 总时间单位
     */
    private String total_time_elapsed_ouom;

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

