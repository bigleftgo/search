package com.hwkj.search.service;

import cn.novelweb.tool.upload.local.pojo.UploadFileParam;
import com.hwkj.search.common.Result;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * com.hwkj.search.service
 *
 * @author Lenovo
 * @CreateTime 2020/9/14 14:23
 */
public interface IFileService {
    /**
     * 上传文件
     *
     * @param file 文件
     */
    Result<Map<String,Object>> uploadFiles(MultipartFile file);

    /**
     * 获取服务器文件
     * @param fileName 文件名
     */
    Result<File> getFile(String fileName);

    /**
     * 获取文件输入流
     * @param fileName 文件名
     */
    InputStream getFileInputStream(String fileName);

    /**
     * 断点续传
     *
     * @param param
     * @param request
     * @return
     */
    Result<Object> breakpointResumeUpload(UploadFileParam param, HttpServletRequest request);

    Result<String> returnUrl(String url);
}
