package com.hwkj.search.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/fileDowload")
public class FileDownloadController {
    /**
     * 下载文件
     * @return
     * @throws Exception
     */
    @RequestMapping("download/{filename}")
    public ResponseEntity<byte[]> download(@PathVariable String filename, @PathVariable String path) throws IOException {
        //下载文件的路径(这里绝对路径)
        String filepath= path;//"F:/download/"+filename;
        File file =new File(filepath);
        //创建字节输入流，这里不实用Buffer类
        InputStream in = new FileInputStream(file);
        //available:获取输入流所读取的文件的最大字节数
        byte[] body = new byte[in.available()];
        //把字节读取到数组中
        in.read(body);
        //设置请求头
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + file.getName());
        //设置响应状态
        HttpStatus statusCode = HttpStatus.OK;
        in.close();
        ResponseEntity<byte[]> entity = new ResponseEntity<byte[]>(body, headers, statusCode);
        return entity;//返回
    }
}
