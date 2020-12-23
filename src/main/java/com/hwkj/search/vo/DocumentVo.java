package com.hwkj.search.vo;

import com.hwkj.search.bean.BaseBean;
import com.hwkj.search.common.CommonCode;
import lombok.Data;

/**
 * com.hwkj.search.vo
 * 收集前端文件对象
 * @author Lenovo
 * @CreateTime 2020/12/21 9:01
 */
@Data
public class DocumentVo extends BaseBean {
    /**
     * 文件id
     */
    private String information_item_id;
    /**
     * 主键写死
     */
    private String info_item_subtype = CommonCode.INFO_ITEM_SUBTYPE_DOC.getDescription();
    /**
     * 文件路径
     */
    private String store_id;
    /**
     * 设计单位id
     */
    private String contact_ba_id;
    /**
     * 标题
     */
    private String title;
    /**
     * 文档类型（管柱图等图片存这里）
     */
    private String document_type;
    /**
     * 设计人名称
     */
    private String creator_ba_id;
    /**
     * 设计日期
     */
    private String origin_date;
    /**
     * 文档后缀
     */
    private String suffix;
}
