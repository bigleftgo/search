package com.hwkj.search.utils;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.sl.extractor.SlideShowExtractor;
import org.apache.poi.sl.usermodel.SlideShow;
import org.apache.poi.ss.extractor.ExcelExtractor;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * com.hwkj.search.utils
 * 文档解析工具类
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 17:34
 */
@Slf4j
public class DocReadUtil {
    //    private static final String savePath = "C:\\HanWei\\数据访问服务20.07.16\\resouce\\Knowledge\\file-manager";
    private static final String savePath = "D:\\HanWei\\数据访问服务20.07.16\\resouce\\Knowledge\\file-manager";

    public static List<String> readWord(List<String> path) throws IOException {
        List<String> list = new ArrayList<>();

        for (String s : path) {
            StringBuilder result = new StringBuilder();
            if (s.endsWith(".doc")) {
                log.info("path is :{}", savePath + s);
                FileInputStream is = new FileInputStream(savePath + s);
                WordExtractor ex = new WordExtractor(is);
                result.append(ex.getText());
                ex.close();
                list.add(result.toString());
            } else if (s.endsWith(".docx")) {
                InputStream fs = new FileInputStream(savePath + s);
                XWPFDocument xdoc = new XWPFDocument(fs);
                XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
                result.append(extractor.getText());
                extractor.close();
                list.add(result.toString());
            } else if (s.endsWith(".xls") || s.endsWith(".xlsx")) {
                ExcelReader reader = ExcelUtil.getReader(savePath + s);
                ExcelExtractor extractor1 = reader.getExtractor();
                String text = extractor1.getText();
                result.append(text);
                list.add(result.toString());
            } else if (s.endsWith(".pdf")) {
                // 新建一个PDF解析器对象
                PDFParser parser = new PDFParser(new RandomAccessFile(new File(savePath + s), "rw"));
                // 对PDF文件进行解析
                parser.parse();
                // 获取解析后得到的PDF文档对象
                PDDocument pdfdocument = parser.getPDDocument();
                // 新建一个PDF文本剥离器
                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setSortByPosition(false); //sort设置为true 则按照行进行读取，默认是false
                // 从PDF文档对象中剥离文本
                String res = stripper.getText(pdfdocument);
                result.append(res);
                list.add(result.toString());
                pdfdocument.close();
            } else if (s.endsWith(".pptx")) {
                InputStream input = new FileInputStream(savePath + s);
                XMLSlideShow xss = new XMLSlideShow(input);
                //得到全部文本
                String pptText = readBySlideShowExtractor(xss);
                result.append(pptText);
                list.add(result.toString());
            } else if (s.endsWith("ppt")) {
                InputStream input = new FileInputStream(savePath + s);
                HSLFSlideShow hss = new HSLFSlideShow(input);
                //得到全部文本
                String pptText = readBySlideShowExtractor(hss);
                result.append(pptText);
                list.add(result.toString());
            }
        }

        return list;
    }

    /**
     * 获取ppt解析文本
     *
     * @param slideShow
     * @return
     */
    private static String readBySlideShowExtractor(SlideShow slideShow) {
        SlideShowExtractor slideShowExtractor = new SlideShowExtractor<>(slideShow);
        return slideShowExtractor.getText();
    }
}
