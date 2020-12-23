package com.hwkj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.hwkj.search.bean.WellActivity;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.service.WellActivityService;
import com.hwkj.search.utils.HttpUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/12/14 10:05
 */
@Service
public class WellActivityServiceImpl implements WellActivityService {
    @Override
    public Result<String> save(List<WellActivity> activities) {
        String post = HttpUtil.httpPost(JSON.toJSONString(activities), "http://192.168.1.180:6618/DasService/DataService/mxalzsk/cs/WELLACTIVITY");
        return Results.newSuccessResult(post);
    }
}
