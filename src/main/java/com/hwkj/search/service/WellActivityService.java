package com.hwkj.search.service;

import com.hwkj.search.bean.WellActivity;
import com.hwkj.search.common.Result;

import java.util.List;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:05
 */
public interface WellActivityService {

    Result<String> save(List<WellActivity> activities);
}
