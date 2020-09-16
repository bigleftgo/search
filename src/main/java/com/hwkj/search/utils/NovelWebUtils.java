package com.hwkj.search.utils;


import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;

/**
 * 提供给NovelWeb工具的相关工具类
 *
 * @author xzr
 * @date 2020/9/15 14:53
 */
public class NovelWebUtils {
    /**
     * 上传文件结果转换为本系统的结果
     *
     * @param result
     * @return
     */
    public static Result<Object> forReturn(cn.novelweb.tool.http.Result<Object> result){
        if("200".equals(result.getCode()) || "201".equals(result.getCode())){
            return Results.newSuccessResult(result.getData(), result.getMessage());
        }else if("206".equals(result.getCode())){
            return Results.newFailResult(ErrorCode.FILE_MISS_CHUNKS, result.getMessage());
        }else{
            return Results.newFailResult(ErrorCode.FILE_UPLOAD, result.getMessage());
        }
    }

}
