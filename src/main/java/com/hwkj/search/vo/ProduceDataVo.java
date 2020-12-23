package com.hwkj.search.vo;

import com.hwkj.search.bean.BaseBean;
import lombok.Data;

/**
 * com.hwkj.search.vo
 * 收集生产数据
 * @author Lenovo
 * @CreateTime 2020/12/21 15:11
 */
@Data
public class ProduceDataVo extends BaseBean {
    /**
     * 项目阶段
     */
    private String pro_phase;
    /**
     * 取值时间
     */
    private String taking_time;
}
