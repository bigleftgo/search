package com.hwkj.search.service.impl;

import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.common.SysConstant;
import com.hwkj.search.service.IFileService;
import com.hwkj.search.utils.EmptyUtils;
import com.hwkj.search.utils.UuidUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/9/14 14:24
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements IFileService {
    @Value("${file.save-path:/data-center/files/}")
    private String savePath;
    @Value("${file.conf-path:/data-center/files/vip-file-manager/conf}")
    private String confFilePath;

    @Override
    public Result<String> uploadFiles(MultipartFile file) {
        try {
            //获取上传文件名
            String fileName = file.getOriginalFilename();
            if (EmptyUtils.basicIsEmpty(fileName)) {
                return Results.newFailResult(ErrorCode.FILE_ERROR, "文件名不能为空");
            }
            if (file.getSize() > SysConstant.MAX_SIZE) {
                return Results.newFailResult(ErrorCode.FILE_ERROR, "文件过大，请使用大文件传输");
            }
            String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
            String newName = UuidUtils.uuid() + suffixName;
            // 重命名文件
            File newFile = new File(savePath, newName);
            // 如果该存储路径为空则新建存储路径
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(newFile);
            // 保存文件信息
            return Results.newSuccessResult("上传完成");
        } catch (Exception e) {
            log.error("上传协议文件出错", e);
        }
        return Results.newFailResult(ErrorCode.FILE_ERROR, "上传失败");
    }

//    @Override
//    public Result<File> getFile(String fileName) {
//        String path = savePath + fileName;
//        File file =new File(path);
//        return Results.newSuccessResult(file);
//    }
}
