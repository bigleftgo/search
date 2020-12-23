package com.hwkj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.hwkj.search.bean.RmInformationItem;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.service.RmInformationItemService;
import com.hwkj.search.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:07
 */
@Service
public class RmInformationItemServiceImpl implements RmInformationItemService {
    @Override
    public Result<String> save(List<RmInformationItem> items) {
        String post = HttpUtil.httpPost(JSON.toJSONString(items), "http://192.168.1.180:6618/DasService/DataService/mxalzsk/cs/RMINFORMATIONITEM");
        return Results.newSuccessResult(post);

    }
}
