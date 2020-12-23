package com.hwkj.search.vo;

import com.hwkj.search.bean.BaseBean;
import lombok.Data;

import java.util.List;

/**
 * com.hwkj.search.vo
 * 工艺/地质/施工VO
 *
 * @author 夏峥嵘
 * @CreateTime 2020/12/16 9:56
 */
@Data
public class KnowledgeVo extends BaseBean {

    /**
     * 知识名称
     */
    private String title_zs;
    /**
     * 知识id
     */
    private String zs_id;
    /**
     * 井号
     */
    private String uwi;
    /**
     * 施工日期
     */
    private String start_date;
    /**
     * 知识描述
     */
    private String abstract1;
    /**
     * 知识标签
     */
    private String reported_keyword;
    /**
     * 生产层位
     */
    private String base_strat_unit_id;
    /**
     * 油气田名称
     */
    private String field_id;
    /**
     * 油藏类型
     */
    private String pool_id;
    /**
     * 单元名称
     */
    private String pden_id;
    /**
     * 套管尺寸
     */
    private String average_value;
    /**
     * 措施类型
     */
    private String activity_type_id;
    /**
     * 提交日期
     */
    private String submit_date;
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
    private String description;
    /**
     * 目前井况
     */
    private String remark;
    /**
     * 项目阶段
     */
    private String pro_phase;
    /**
     * 取值时间
     */
    private String taking_time;
    /**
     * 活动序号
     */
    private Integer act_no;
    /**
     * 井状态集合
     */
    private List<WellStatusVo> statuses;
    /**
     * 单位id
     */
    private String ba_organization_id;
    /**
     * 套管值
     */
    private String misc_data_desc;
    /**
     * 提交状态
     */
    private String status;
    /**
     * 文档信息
     */
    private List<DocumentVo> docs;
    /**
     * 生产数据
     */
    private List<ProduceDataVo> produce;
}
