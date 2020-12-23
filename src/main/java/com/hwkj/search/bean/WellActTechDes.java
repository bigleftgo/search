package com.hwkj.search.bean;

import java.util.Date;
import lombok.Data;

@Data
public class WellActTechDes {
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
     * 工艺措施内容
     */
    private String con_pro_measures;

    /**
     * 设计举升方式
     */
    private String des_lift_method;

    /**
     * 设计抽油泵类型
     */
    private String des_pump_type;

    /**
     * 设计泵径
     */
    private String des_pump_diameter;

    /**
     * 原井况
     */
    private String ori_well_condition;

    /**
     * 设计抽油杆类型
     */
    private String des_suck_rod_type;

    /**
     * 设计抽油杆材质
     */
    private String des_suck_rod_mat;

    /**
     * 设计油管类型
     */
    private String des_tub_type;

    /**
     * 是否加重杆
     */
    private String is_wei_bar;

    /**
     * 是否加扶正器
     */
    private String is_whe_add_cen;

    /**
     * 杆组合
     */
    private String rod_combination;

    /**
     * 管组合
     */
    private String tube_assembly;

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

    /**
     * 设计螺杆泵类型
     */
    private String des_screw_pump_type;

    /**
     * 设计螺杆泵型号
     */
    private String des_screw_pump_model;

    /**
     * 杆设计油管组合
     */
    private String rod_des;

    /**
     * 设计电潜泵类型
     */
    private String des_esp_types;

    /**
     * 设计电潜泵型号
     */
    private String des_esp_model;

    /**
     * 潜油电机类型
     */
    private String sub_motor;

    /**
     * 保护器
     */
    private String protector;

    /**
     * 分离器
     */
    private String separator;

    /**
     * 设计抽油杆组合
     */
    private String des_suck_rod;

    /**
     * 地面驱动装置
     */
    private String ground_drives;

    /**
     * 有效标识
     */
    private String active_ind;
}

