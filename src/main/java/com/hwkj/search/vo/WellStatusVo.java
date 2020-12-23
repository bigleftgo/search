package com.hwkj.search.vo;

import com.hwkj.search.bean.BaseBean;
import lombok.Data;

/**
 * com.hwkj.search.vo
 * 收集前端井状态vo
 * @author Lenovo
 * @CreateTime 2020/12/17 16:54
 */
@Data
public class WellStatusVo extends BaseBean {
    /**
     * 井别
     */
    private String status;
    /**
     * 状态类型
     */
    private String status_type;
    /**
     * 随机id
     */
    private String status_id;
}
