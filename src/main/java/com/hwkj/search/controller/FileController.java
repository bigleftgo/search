package com.hwkj.search.controller;

import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.RestResponse;
import com.hwkj.search.common.RestResponses;
import com.hwkj.search.service.IFileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * com.hwkj.search.controller
 *
 * @author Lenovo
 * @CreateTime 2020/9/14 9:40
 */
@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {
    private final IFileService fileService;

    /**
     * 文件上传
     *
     * @param file 文件
     * @return 返回成功或者失败
     */
    @PostMapping("/upload")
    public RestResponse<String> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return RestResponses.newFailResponse(ErrorCode.INVALID_PARAMETER, "文件不能为空");
        }
        return RestResponses.newResponseFromResult(fileService.uploadFiles(file));
    }

//    /**
//     * 文件下载
//     *
//     * @param id
//     * @param isSource
//     * @param request
//     * @param response
//     */
//    @GetMapping(value = "/download")
//    public void viewFilesImage(@RequestParam(required = false) String fileName, HttpServletRequest request, HttpServletResponse response) {
//        OutputStream outputStream = null;
//        InputStream inputStream = null;
//        try {
////            Result<Files> fileDetails = fileService.getFileDetails(id);
//            Result<File> file = fileService.getFile(fileName);
//            if (!fileDetails.isSuccess()) {
//                throw new SystemException(fileDetails.getErrorCode().getCode(), fileDetails.getDescription());
//            }
//            String filename = (!EmptyUtils.basicIsEmpty(isSource) && isSource) ? fileDetails.getData().getFileName() : fileDetails.getData().getFilePath();
//            inputStream = fileService.getFileInputStream(id);
//            response.setHeader("Content-Disposition", "attachment;filename=" + EncodingUtils.convertToFileName(request, filename));
//            // 获取输出流
//            outputStream = response.getOutputStream();
//            IOUtils.copy(inputStream, outputStream);
//        } catch (IOException e) {
//            log.error("文件下载出错", e);
//        } finally {
//            try {
//                if (inputStream != null) {
//                    inputStream.close();
//                }
//                if (outputStream != null) {
//                    outputStream.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
}
