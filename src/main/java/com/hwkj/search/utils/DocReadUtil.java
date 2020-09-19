package com.hwkj.search.utils;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
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
public class DocReadUtil {

    public static List<String> readWord(List<String> path) {
        List<String> list = new ArrayList<>();
        try {
            for (String s : path) {
                StringBuilder result = new StringBuilder();
                if (s.endsWith(".doc")) {
                    InputStream is = new FileInputStream(s);
                    WordExtractor ex = new WordExtractor(is);
                    result.append(ex.getText());
                    ex.close();
                    list.add(result.toString());
                } else if (s.endsWith(".docx")) {
                    InputStream fs = new FileInputStream(s);
                    XWPFDocument xdoc = new XWPFDocument(fs);
                    XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
                    result.append(extractor.getText());
                    extractor.close();
                    list.add(result.toString());
                } else if (s.endsWith(".xls") || s.endsWith(".xlsx")) {
                    InputStream inp = new FileInputStream(s);
                    HSSFWorkbook wb = new HSSFWorkbook(new POIFSFileSystem(inp));
                    ExcelExtractor extractor = new ExcelExtractor(wb);
                    extractor.setFormulasNotResults(true);
                    extractor.setIncludeSheetNames(false);
                    String text = extractor.getText();
                    result.append(text);
                    list.add(result.toString());
                    wb.close();
                } else if (s.endsWith(".pdf")) {
                    // 新建一个PDF解析器对象
                    PDFParser parser = new PDFParser(new RandomAccessFile(new File(s), "rw"));
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
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
