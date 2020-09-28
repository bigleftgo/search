package com.hwkj.search.controller;

import cn.novelweb.tool.upload.local.pojo.UploadFileParam;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.common.Result;
import com.hwkj.search.config.SystemException;
import com.hwkj.search.service.IFileService;
import com.hwkj.search.utils.EncodingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * com.hwkj.search.controller
 *
 * @author xzr
 * @CreateTime 2020/9/14 9:40
 */
@RestController
@Slf4j
@CrossOrigin
public class FileController {

    @Autowired
    private IFileService fileService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 返回成功或者失败
     */
    @PostMapping("/upload")
    public RestResponse<Map<String, Object>> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return RestResponses.newFailResponse(ErrorCode.INVALID_PARAMETER, "文件不能为空");
        }
        return RestResponses.newResponseFromResult(fileService.uploadFiles(file));
    }

    /**
     * 文件下载
     *
     * @param request  设置IE适配
     * @param response 响应对象
     */
    @GetMapping(value = "/download")
    public void viewFilesImage(@RequestParam String path,@RequestParam String fileName, HttpServletRequest request, HttpServletResponse response) {
        OutputStream outputStream = null;
        InputStream inputStream = null;
        try {
            //判断文件是否存在
            Result<File> file = fileService.getFile(path);
            if (!file.isSuccess()) {
                throw new SystemException(file.getErrorCode().getCode(), file.getDescription());
            }

            //获取输入流
            inputStream = fileService.getFileInputStream(path,fileName);
            response.setHeader("Content-Disposition", "attachment;path=" + EncodingUtils.convertToFileName(request, file.getData().getName()));
            // 获取输出流
            outputStream = response.getOutputStream();
            IOUtils.copy(inputStream, outputStream);
        } catch (IOException e) {
            log.error("文件下载出错", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 断点续传方式上传文件：用于大文件上传
     *
     * @param param   断点上传
     * @param request 请求体
     * @return 返回结果集
     */
    @PostMapping(value = "/breakpoint-upload", consumes = "multipart/*", headers = "content-type=multipart/form-data", produces = "application/json;charset=UTF-8")
    public RestResponse<Object> breakpointResumeUpload(UploadFileParam param, HttpServletRequest request) {
        return RestResponses.newResponseFromResult(fileService.breakpointResumeUpload(param, request));
    }

}
