package com.hwkj.search.controller;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wltea.analyzer.lucene.IKAnalyzer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
@CrossOrigin
@SpringBootApplication
@Controller
public   class  LuceneIndex {




    private String ids[]={"1","2"};
    private String names[]={"湖人","开拓者"};
    /*{private String descs=readTxt("E:\\uploadFile\\2020-09-02\\qwert.docx");
            "北京时间8月30日，湖人与开拓者迎来系列赛第5场！此战前，湖人队3:1拿到了赛点，只要拿下本场胜利，他们就将进入第2轮，还因火箭与雷霆系列赛未分出胜负，获得一定的休息时间。",
            "开拓者局势则非常波动，他们不仅1:3落后，士气和实力都处于下风，更重要的是球队大将利拉德已经确定缺席剩下的所有比赛，这又极大增加了球队的获胜难度，本场他们很有可能会被湖人直接淘汰！"
     };*/
    private Directory dir;



    public static String readFile(String filePath) {
        String fileContent = "";
        try {
            File f = new File(filePath);
            if (f.isFile() && f.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                BufferedReader reader = new BufferedReader(read);
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += line;
                }
                read.close();
            }
        } catch (Exception e) {
            System.out.println("读取文件内容操作出错");
            e.printStackTrace();
        }
        return fileContent;
    }



    public static String readWord(String path) {
    String buffer = "";
        try {
        if (path.endsWith(".doc")) {
            InputStream is = new FileInputStream(new File(path));
            WordExtractor ex = new WordExtractor(is);
            buffer = ex.getText();
            ex.close();
        } else if (path.endsWith("docx")) {
            OPCPackage opcPackage = POIXMLDocument.openPackage(path);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            buffer = extractor.getText();
            extractor.close();
        } else if (path.endsWith("xlsx")) {

        }else if (path.endsWith("xlsx")) {

        }else if (path.endsWith("xlsx")) {

        }else if (path.endsWith("xlsx")) {

        }else {
            System.out.println("此文件不是word文件！");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

        return buffer;
}


    /**
     * 获取IndexWriter实例
     * @return
     * @throws Exception
     */
    private IndexWriter getWriter()throws Exception{
        //Analyzer analyzer=new StandardAnalyzer(); // 标准分词器
        IKAnalyzer analyzer=new IKAnalyzer();
        IndexWriterConfig iwc=new IndexWriterConfig(analyzer);
        IndexWriter writer=new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     * 生成索引
     * @param indexDir
     * @throws Exception
     */
    private void index(String indexDir,String path,String id)throws Exception{
        String descs=readWord("E:\\uploadFile\\2020-09-02\\2020年胜利研发中心项目计划.xlsx");
        dir= FSDirectory.open(Paths.get(indexDir));
        IndexWriter writer=getWriter();
        for(int i=0;i<1;i++){
            Document doc=new Document();
            //doc.add(new Field("id",id, Field.Store.YES,Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new StringField("id", id, Field.Store.YES));
            doc.add(new StringField("name",names[i],Field.Store.YES));
            doc.add(new StringField("age", id, Field.Store.YES));
            doc.add(new StringField("yx",names[i],Field.Store.YES));
            doc.add(new TextField("desc", descs, Field.Store.YES));
            writer.addDocument(doc); // 添加文档
        }
        writer.close();
    }



    @RequestMapping("/uploadFiles")
    @ResponseBody
    public void uploadFile(HttpServletRequest request, MultipartFile file,  HttpServletResponse response) throws  Exception {

        //文件后缀
        //获取扩展名
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());
        //每天创建一个日期文件
        String dataDir=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //获取上传文件名称
        String oldfilename=file.getOriginalFilename();
        //获取扩展名
        String exts = FilenameUtils.getExtension(oldfilename);
        //名称唯一化
        String newfilename= UUID.randomUUID().toString().replace("-","") +"."+exts;
        String id= UUID.randomUUID().toString().replace("-","");
        //上传文件
        String path="E:\\uploadFile\\"+dataDir+"\\"+newfilename;
        File file1 = new File(path);

        if(file1.getParentFile().exists()){
        }else{
            file1.getParentFile().mkdir();
        }
        try {
         //保存文件
         file.transferTo(file1);

        new LuceneIndex().index("E:\\uploadFile\\2020-09-02\\index\\lucene2",path,id);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * 下载文件
     * @return
     * @throws Exception
     */
    // 文件下载
    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request,@RequestParam("filename") String filename) throws Exception {
        // 下载路径
        String path = "E:\\uploadFile\\2020-09-02\\";
        File file = new File(path + File.separator + filename);


        HttpHeaders headers = new HttpHeaders();
        // 解决中文乱码
        String downloadfile = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        // 以下载方式打开文件
        headers.setContentDispositionFormData("attachment", downloadfile);
        // 二进制流
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }

}
