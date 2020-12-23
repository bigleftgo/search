package com.hwkj.search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * CommonUtils
 *
 * @author 夏峥嵘
 * 2020/8/8 15:59
 **/
public class CommonUtils {
    /**
     * 转换Json对象为集合
     *
     * @param str     json
     * @param classzz 类型
     * @param <T>     类型
     * @return 返回集合
     */
    public static <T> List<T> ConvertJsonDatas(String str, Class<T> classzz) {
        String data = JSON.parseObject(str).getString("data");
        JSONArray objects = JSON.parseArray(data);
        String s = objects.getString(0);
        String datas = JSON.parseObject(s).getString("datas");
        List<T> list = JSON.parseArray(datas, classzz);
        return list;
    }

    /**
     * 转换Json对象为集合
     *
     * @param str     json
     * @param classzz 类型
     * @param <T>     类型
     * @return 返回集合
     */
    public static <T> T ConvertJsonDatasByOne(String str, Class<T> classzz) {
        String data = JSON.parseObject(str).getString("data");
        JSONArray objects = JSON.parseArray(data);
        String s = objects.getString(0);
        String datas = JSON.parseObject(s).getString("datas");
        List<T> list = JSON.parseArray(datas, classzz);
        return list.stream().findFirst().orElse(null);
    }

    /**
     * 转换Json对象，判断success的状态
     *
     * @param <T> 类型
     * @param str json
     * @return 返回集合
     */
    public static <T> RestResponse ConvertJsonJudgmentIsSuccess(String... str) {
        for (String s : str) {
            if (JSONObject.parseObject(s).getString("isSucceed").equals("false")) {
                return RestResponses.newFailResponse(ErrorCode.SYSTEM_ERROR,"保存失败");
            }
        }
            return RestResponses.newSuccessResponse("保存成功",null);
    }


}
