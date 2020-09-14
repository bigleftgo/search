package com.hwkj.search.controller;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.ConnectException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 利用jodconverter(基于OpenOffice服务)将文件(*.doc、*.docx、*.xls、*.ppt)转化为html格式或者pdf格式，
 * 使用前请检查OpenOffice服务是否已经开启, OpenOffice进程名称：soffice.exe | soffice.bin
 *
 * @author yjclsx
 */

@CrossOrigin
@SpringBootApplication
@Controller
public class testBean {

    private static testBean doc2HtmlUtil;

    /**
     * 获取Doc2HtmlUtil实例
     */
    public static synchronized testBean getDoc2HtmlUtilInstance() {
        if (doc2HtmlUtil == null) {
            doc2HtmlUtil = new testBean();
        }
        return doc2HtmlUtil;
    }

    /**
     * 转换文件成html
     *
     * @param fromFileInputStream:
     * @throws IOException
     */
    public String file2Html(InputStream fromFileInputStream, String toFilePath,String type) throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String docFileName = null;
        String htmFileName = null;
        if("doc".equals(type)){
            docFileName = "doc_" + timesuffix + ".doc";
            htmFileName = "doc_" + timesuffix + ".html";
        }else if("docx".equals(type)){
            docFileName = "docx_" + timesuffix + ".docx";
            htmFileName = "docx_" + timesuffix + ".html";
        }else if("xls".equals(type)){
            docFileName = "xls_" + timesuffix + ".xls";
            htmFileName = "xls_" + timesuffix + ".html";
        }else if("ppt".equals(type)){
            docFileName = "ppt_" + timesuffix + ".ppt";
            htmFileName = "ppt_" + timesuffix + ".html";
        }else{
            return null;
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.exists())
            htmlOutputFile.delete();
        htmlOutputFile.createNewFile();
        if (docInputFile.exists())
            docInputFile.delete();
        docInputFile.createNewFile();
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
        docInputFile.delete();
        return htmFileName;
    }

    /**
     * 转换文件成pdf
     *
     * @param :
     * @param
     * @throws IOException
     */
    @RequestMapping("/ylFile")
    @ResponseBody
    public String file2pdf(String toFilePath, String type) throws IOException {

        String command = "C:\\Program Files (x86)\\OpenOffice 4\\" + "program\\soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
        try {
            Process pro = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println("OpenOffice服务启动失败");
        }

        //testBean coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fromFileInputStream = null;

        file = new File("E:\\uploadFile\\2020-09-02\\qwert.docx");
        //file = new File("E:\\uploadFile\\2020-09-02\\docx");
        fromFileInputStream = new FileInputStream(file);


        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timesuffix = sdf.format(date);
        String docFileName = null;
        String htmFileName = null;
        if("doc".equals(type)){
            docFileName = "doc_" + timesuffix + ".doc";
            htmFileName = "doc_" + timesuffix + ".pdf";
        }else if("docx".equals(type)){
            docFileName = "docx_" + timesuffix + ".docx";
            htmFileName = "docx_" + timesuffix + ".pdf";
        }else if("xls".equals(type)){
            docFileName = "xls_" + timesuffix + ".xls";
            htmFileName = "xls_" + timesuffix + ".pdf";
        }else if("ppt".equals(type)){
            docFileName = "ppt_" + timesuffix + ".ppt";
            htmFileName = "ppt_" + timesuffix + ".pdf";
        }else{
            return null;
        }

        File htmlOutputFile = new File(toFilePath + File.separatorChar + htmFileName);
        File docInputFile = new File(toFilePath + File.separatorChar + docFileName);
        if (htmlOutputFile.getParentFile().exists()){
            htmlOutputFile.delete();
            htmlOutputFile.getParentFile().createNewFile();
        }else{
            docInputFile.getParentFile().mkdir();
        }

        if (docInputFile.getParentFile().exists()){
            docInputFile.delete();
        docInputFile.getParentFile().createNewFile();
        }else{
            docInputFile.getParentFile().mkdir();
        }
        /**
         * 由fromFileInputStream构建输入文件
         */
        try {
            OutputStream os = new FileOutputStream(docInputFile);
            int bytesRead = 0;
            byte[] buffer = new byte[1024 * 8];
            while ((bytesRead = fromFileInputStream.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }

            os.close();
            fromFileInputStream.close();
        } catch (IOException e) {
        }

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
        } catch (ConnectException e) {
            System.err.println("文件转换出错，请检查OpenOffice服务是否启动。");
        }
        // convert
        DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
        converter.convert(docInputFile, htmlOutputFile);
        connection.disconnect();
        // 转换完之后删除word文件
       
        return "{\"path\":\"E:/uploadFile/2020-09-02/docx/"+ htmFileName +"\"}";
    }


    public void ylFile(InputStream fromFileInputStream, String toFilePath,String type) throws IOException {

    }






    public static void main(String[] args) throws IOException {
        String command = "C:\\Program Files (x86)\\OpenOffice 4\\" + "program\\soffice -headless -accept=\"socket,host=127.0.0.1,port=8100;urp;\" -nofirststartwizard";
        try {
            Process pro = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            System.out.println("OpenOffice服务启动失败");
        }

        testBean coc2HtmlUtil = getDoc2HtmlUtilInstance();
        File file = null;
        FileInputStream fileInputStream = null;

        file = new File("E:\\uploadFile\\2020-09-02\\qwert.docx");
        fileInputStream = new FileInputStream(file);
//   coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/xls","xls");
       // coc2HtmlUtil.file2pdf(fileInputStream, "E:\\uploadFile\\2020-09-02\\docx","docx");

        /*file = new File("D:/poi-test/test.doc");
        fileInputStream = new FileInputStream(file);
//   coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/doc","doc");
        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/doc","doc");

        file = new File("D:/poi-test/周报模版.ppt");
        fileInputStream = new FileInputStream(file);
//   coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/ppt","ppt");
        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/ppt","ppt");

        file = new File("D:/poi-test/test.docx");
        fileInputStream = new FileInputStream(file);
//   coc2HtmlUtil.file2Html(fileInputStream, "D:/poi-test/openOffice/docx","docx");
        coc2HtmlUtil.file2pdf(fileInputStream, "D:/poi-test/openOffice/docx","docx");*/

    }


}
