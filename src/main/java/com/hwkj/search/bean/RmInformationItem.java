package com.hwkj.search.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RmInformationItem {
    /**
     * 记录子类型
     */
    private String info_item_subtype;

    /**
     * 记录ID
     */
    private String information_item_id;

    /**
     * 条目分类
     */
    private String item_category;

    /**
     * 条目子分类
     */
    private String item_sub_category;

    /**
     * 标题
     */
    private String title;

    /**
     * 版本号
     */
    private String version_num;

    /**
     * 摘要
     */
    @TableField("abstract")
    private String abstract1;

    /**
     * 访问条件
     */
    private String access_condition;

    /**
     * 接收日期
     */
    private String accepted_date;

    /**
     * 可用日期
     */
    private String available_date;

    /**
     * 修改日期
     */
    private String modified_date;

    /**
     * 初始日期
     */
    private String origin_date;

    /**
     * 公布日期
     */
    private String publish_date;

    /**
     * 提交日期
     */
    private String submit_date;

    /**
     * 发行日期
     */
    private String issue_date;

    /**
     * 坐标ID
     */
    private String coord_acquisition_id;

    /**
     * 版权日期
     */
    private String copyright_date;

    /**
     * 地理坐标系统ID
     */
    private String geog_coord_system_id;

    /**
     * 分组标志
     */
    private String group_ind;

    /**
     * 本地坐标系ID
     */
    private String local_coord_system_id;

    /**
     * 地图坐标系统ID
     */
    private String map_coord_system_id;

    /**
     * 最大纬度
     */
    private BigDecimal max_latitude;

    /**
     * 最大经度
     */
    private BigDecimal max_longitude;

    /**
     * 最小纬度
     */
    private BigDecimal min_latitude;

    /**
     * 最小经度
     */
    private BigDecimal min_longitude;

    /**
     * 目标
     */
    private String purpose;

    /**
     * 安全描述
     */
    private String security_desc;

    /**
     * 引用编号
     */
    private String reference_num;

    /**
     * 源文献ID
     */
    private String source_document_id;

    /**
     * 保存时间
     */
    private String time_period_desc;

    /**
     * 使用条件
     */
    private String use_condition;

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

