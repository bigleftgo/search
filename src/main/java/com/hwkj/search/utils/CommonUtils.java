package com.hwkj.search.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

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
     * @param str json
     * @param classzz 类型
     * @param <T> 类型
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
     * @param str json
     * @param classzz 类型
     * @param <T> 类型
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

//    public static String
}
