package com.hwkj.search.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * com.hwkj.search.vo
 * 返回前端的对象
 * @author Lenovo
 * @CreateTime 2020/9/19 15:56
 */
@Data
public class SearchVo {
    /**
     * 工艺标题
     */
    private String gysjsjmc;
    /**
     * 地质标题
     */
    private String dzsjsjmc;
    /**
     * 施工标题
     */
    private String sgsjsjmc;
    /**
     * 工艺设计人
     */
    private String gysjsjr;
    /**
     * 地质设计人
     */
    private String dzsjsjr;
    /**
     * 施工设计人
     */
    private String sgsjsjr;

    /**
     * 高亮内容
     */
    private String desc;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 查询关键字
     */
    private String search;
    /**
     * 知识描述
     */
    private String k_des;
    /**
     * 工艺来源
     */
    private String gysjsjdw;
    /**
     * 地质来源
     */
    private String dzsjsjdw;
    /**
     * 施工来源
     */
    private String sgsjsjdw;
    /**
     * 工艺设计时间
     */
    private String gysjrq;
    /**
     * 地质设计时间
     */
    private String dzsjrq;
    /**
     * 施工设计时间
     */
    private String sgsjrq;
    /**
     * 工艺设计类型
     */
    private String gysjlx;
    /**
     * 地质设计类型
     */
    private String dzsjlx;
    /**
     * 施工设计类型
     */
    private String sgsjlx;
    /**
     * 措施类型
     */
    private String cslx;
    /**
     * 措施大类
     */
    private String csdl;


}
