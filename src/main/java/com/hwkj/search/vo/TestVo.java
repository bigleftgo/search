package com.hwkj.search.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hwkj.search.bean.BaseBean;
import lombok.Data;

/**
 * com.hwkj.search.vo
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:21
 */
@Data
public class TestVo extends BaseBean {
    private String uwi;
    private String source;
    private Integer activity_obs_no;
    private String start_date;
    private String activity_type_id;
    private String info_item_subtype;
    private String information_item_id;
    private String title;
    private String abstract1;
    private Integer content_obs_no;
    private String well_activity_source;
}
