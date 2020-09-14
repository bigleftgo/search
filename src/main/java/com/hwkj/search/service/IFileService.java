package com.hwkj.search.service;

import com.hwkj.search.common.Result;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;

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
    Result<String> uploadFiles(MultipartFile file);

    /**
     * 获取服务器文件
     * @param fileName 文件名
     */
//    Result<Files> getFile(String fileName);
}
