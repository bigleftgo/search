package com.hwkj.search.vo;

import lombok.Data;

/**
 * com.hwkj.search.vo
 *
 * @author Lenovo
 * @CreateTime 2020/12/8 14:24
 */
@Data
public class HwkjSearchVo {
    /**
     * 文档名称
     */
    private String wdmc;
    /**
     * 描述
     */
    private String ms;
    /**
     * 知识标签
     */
    private String zsbq;
    /**
     * 编写部门
     */
    private String bxbm;
    /**
     * 文档类型
     */
    private String wdlx;
    /**
     * 编写人
     */
    private String bxr;
    /**
     * 编写日期
     */
    private String bxrq;
    /**
     * 项目名称
     */
    private String xmmc;
    /**
     * 所属类型
     */
    private String sslx;
    /**
     * 内容
     */
    private String desc;
    /**
     * 检索大类
     */
    private String jsdl;
    /**
     * 文件id
     */
    private String fileId;

}
