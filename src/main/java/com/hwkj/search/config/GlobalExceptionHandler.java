package com.hwkj.search.config;

import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;

import com.hwkj.search.common.RestResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理，捕获所有Controller中抛出的异常。
 *
 * @author xzr
 * @date 2019/7/19
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理自定义异常，其他未处理的异常，按系统异常处理
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public RestResponse<String> exceptionHandler(Exception e){
		log.error("捕获到系统异常", e);
		return RestResponses.newFailResponse(ErrorCode.SYSTEM_ERROR, e.getMessage());
	}
}
