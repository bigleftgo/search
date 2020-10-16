package com.hwkj.search.bean;

import lombok.Data;

import java.util.Date;

/**
 * com.hwkj.search.bean
 * 知识表数据
 *
 * @author Lenovo
 * @CreateTime 2020/9/28 17:24
 */
@Data
public class ProKnowledge {
    /**
     * 记录ID
     */
    private String information_item_id;
    /**
     * 井号
     */
    private String uwi;
    /**
     * 施工日期
     */
    private Date construction_date;
    /**
     * 知识名称
     */
    private String knowledge_name;
    /**
     * 知识描述
     */
    private String description;
    /**
     * 井型
     */
    private String profile_type;
    /**
     * 生产层位
     */
    private String prod_horizon;
    /**
     * 油气田
     */
    private String field;
    /**
     * 注水层位
     */
    private String water_horizon;
    /**
     * 生产井别
     */
    private String production_well_type;
    /**
     * 油藏类型
     */
    private String reservoir_type;
    /**
     * 驱动类型
     */
    private String driving_type;
    /**
     * 井类型
     */
    private String well_type;
    /**
     * 单元名称
     */
    private String name_unit;
    /**
     * 套管尺寸
     */
    private String casing_size;
    /**
     * 措施类型
     */
    private String measures_type;
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
    private Integer des_pump_diameter;
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
    private String des_suck_rod_material;
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
     * 上修原因
     */
    private String on_repa_reason;
    /**
     * 目前井况
     */
    private String well_conditions;
    /**
     * 地质设计文件ID
     */
    private String file_id_geodes;
    /**
     * 地质设计名称
     */
    private String title_geodes;
    /**
     * 地质设计单位
     */
    private String units_geodes;
    /**
     * 地质设计人
     */
    private String user_geodes;
    /**
     * 地质设计日期
     */
    private Date date_geodes;
    /**
     * 工艺设计文件ID
     */
    private String file_id_techdes;
    /**
     * 工艺设计名称
     */
    private String title_techdes;
    /**
     * 工艺设计单位
     */
    private String units_techdes;
    /**
     * 工艺设计人
     */
    private String user_techdes;
    /**
     * 工艺设计日期
     */
    private Date date_techdes;
    /**
     * 施工设计文件ID
     */
    private String file_id_consdes;
    /**
     * 施工设计名称
     */
    private String title_consdes;
    /**
     * 施工设计单位
     */
    private String units_consdes;
    /**
     * 施工设计人
     */
    private String user_consdes;
    /**
     * 施工设计日期
     */
    private Date date_consdes;
    /**
     * 知识标签
     */
    private String knowledge_label;
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
     * 单位名称
     */
    private String dwmc;
    /**
     * 管柱图id
     */
    private String gztid;
    /**
     * 新工艺介绍
     */
    private String xgyjsid;
}
