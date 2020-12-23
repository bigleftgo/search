package com.hwkj.search.service;

import com.hwkj.search.bean.RmInfoItemContent;
import com.hwkj.search.common.Result;

import java.util.List;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:06
 */
public interface RmInfoItemContentService {
    Result<String> save(List<RmInfoItemContent> contents);
}
