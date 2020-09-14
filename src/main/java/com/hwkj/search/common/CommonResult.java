package com.hwkj.search.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * CommonResult
 *
 * @author xzr
 * 2020/7/21 15:25
 **/
@Data
public class CommonResult {
    private Integer code;

    private String message;

    private Object data;

    private CommonResult() {
    }

    public static CommonResult success() {
        CommonResult r = new CommonResult();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        return r;
    }

    public static CommonResult success(Object data) {
        CommonResult r = new CommonResult();
        r.setCode(ResultCodeEnum.SUCCESS.getCode());
        r.setMessage(ResultCodeEnum.SUCCESS.getMessage());
        r.setData(data);
        return r;
    }

    public static CommonResult error() {
        CommonResult r = new CommonResult();
        r.setCode(ResultCodeEnum.UNKNOWN_REASON.getCode());
        r.setMessage(ResultCodeEnum.UNKNOWN_REASON.getMessage());
        return r;
    }

    public static CommonResult setResult(ResultCodeEnum resultCodeEnum) {
        CommonResult r = new CommonResult();
        r.setCode(resultCodeEnum.getCode());
        r.setMessage(resultCodeEnum.getMessage());
        return r;
    }

    public CommonResult message(String message) {
        this.setMessage(message);
        return this;
    }

    public CommonResult code(Integer code) {
        this.setCode(code);
        return this;
    }

    public CommonResult data(Object data) {
        this.data = data;
        return this;
    }

    public static CommonResult judgeResult(List<String> stringList) {
        for (String s : stringList) {
            if (JSONObject.parseObject(s).getString("isSucceed").equals("false")) {
                return CommonResult.error().message("保存失败");
            }
        }
        return CommonResult.success();
    }

    public static CommonResult judgeResult(String... strings) {
        for (String str : strings) {
            if (JSONObject.parseObject(str).getString("isSucceed").equals("false")) {
                return CommonResult.error().message("保存失败");
            }
        }
        return CommonResult.success();
    }
}
