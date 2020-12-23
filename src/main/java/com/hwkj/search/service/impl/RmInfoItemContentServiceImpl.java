package com.hwkj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.hwkj.search.bean.RmInfoItemContent;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.service.RmInfoItemContentService;
import com.hwkj.search.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:06
 */
@Service
public class RmInfoItemContentServiceImpl implements RmInfoItemContentService {
    @Override
    public Result<String> save(List<RmInfoItemContent> contents) {
        String post = HttpUtil.httpPost(JSON.toJSONString(contents), "http://192.168.1.180:6618/DasService/DataService/mxalzsk/cs/RMINFOITEMCONTENT");
        return Results.newSuccessResult(post);
    }
}
