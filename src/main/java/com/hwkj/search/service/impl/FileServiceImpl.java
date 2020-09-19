package com.hwkj.search.service.impl;

import cn.novelweb.tool.upload.local.LocalUpload;
import cn.novelweb.tool.upload.local.pojo.UploadFileParam;
import com.alibaba.fastjson.JSONObject;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.common.SysConstant;
import com.hwkj.search.service.IFileService;
import com.hwkj.search.utils.DateUtil;
import com.hwkj.search.utils.EmptyUtils;
import com.hwkj.search.utils.NovelWebUtils;
import com.hwkj.search.utils.UuidUtils;
import com.qcloud.cos.utils.Md5Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.security.provider.MD5;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


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
    @Value("${file.save-path}")
    private String savePath;
    @Value("${file.conf-path}")
    private String confFilePath;
    @Value("${md5.secure}")
    private String secure;

    private final String http = "http://192.168.37.6:8010/";

    /**
     * 文档上传
     *
     * @param file 文件
     * @return
     */
    @Override
    public Result<Map<String, Object>> uploadFiles(MultipartFile file) {
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
            // 重命名文件,根据日期创建文件夹
            String dir = DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD);
            File newFile = new File(savePath + File.separator + dir, newName);
            // 如果该存储路径为空则新建存储路径
            if (!newFile.getParentFile().exists()) {
                newFile.getParentFile().mkdirs();
            }
            // 文件写入
            file.transferTo(newFile);
            // 保存文件信息
            HashMap<String, Object> map = new HashMap<>();
            map.put("path", newFile);
            map.put("name",newFile.getName());
            return Results.newSuccessResult(map, "上传完成");
        } catch (Exception e) {
            log.error("上传协议文件出错", e);
        }
        return Results.newFailResult(ErrorCode.FILE_ERROR, "上传失败");
    }

    /**
     * 获取文件
     *
     * @param fileName 文件名
     * @return
     */
    @Override
    public Result<File> getFile(String fileName) {
        //文件路径
        String path = savePath + File.separator + fileName;
        File file = new File(path);
        return Results.newSuccessResult(file);
    }

    /**
     * 获取文件流
     *
     * @param fileName 文件名
     * @return
     */
    @Override
    public InputStream getFileInputStream(String fileName) {
        try {
            //根据文件名获取本地文件信息
            File file = new File(savePath + File.separator + fileName);
            return new FileInputStream(file);
        } catch (Exception e) {
            log.error("获取文件输入流出错", e);
        }
        return null;
    }

    /**
     * 断点续传
     *
     * @param param
     * @param request
     * @return
     */
    @Override
    public Result<Object> breakpointResumeUpload(UploadFileParam param, HttpServletRequest request) {
        try {
            param.setMd5(DigestUtils.md5DigestAsHex(secure.getBytes()));
            String fileName = param.getFile().getOriginalFilename();
            String suffixName = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".")) : null;
            String newName = UuidUtils.uuid() + suffixName;
            param.setName(newName);
            // 这里的 chunkSize(分片大小) 要与前端传过来的大小一致
            cn.novelweb.tool.http.Result result = LocalUpload
                    .fragmentFileUploader(param, confFilePath, savePath + File.separator + DateUtil.parseDateToStr(new Date(), DateUtil.DATE_FORMAT_YYYY_MM_DD), 5242880L, request);
            return NovelWebUtils.forReturn(result);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return Results.newFailResult(ErrorCode.FILE_UPLOAD, "上传失败");
    }

}
