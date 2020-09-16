package com.hwkj.search.service.impl;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.utils.DateUtil;
import com.hwkj.search.utils.DocReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.nio.file.Paths;
import java.util.Date;

/**
 * com.hwkj.search.service.impl
 *
 * @author Lenovo
 * @CreateTime 2020/9/15 16:18
 */
@Service
@Slf4j
public class ILuceneServiceImpl implements ILuceneService {

    @Value("${file.index-path}")
    private String indexPath;

    @Override
    public void createIndex(Knowledge k) {
        //创建文档对象
        Document document = new Document();
        //获取知识上传的文件内容
        String content = DocReadUtil.readWord(k.getPath());
        if (!StringUtils.isEmpty(content)) {
            //建立内容索引
            document.add(new TextField("desc", content, Field.Store.YES));
        }
        //建立名称索引
        if (!k.getNames().isEmpty()){
            for (String name : k.getNames()) {
                //名称是固定的，不需要被分词
                document.add(new StringField("name", name, Field.Store.YES));
            }
        }
        //建立知识值索引
        if (!k.getValues().isEmpty()){
            for (String value : k.getValues()) {
                document.add(new TextField("value", value, Field.Store.YES));
            }
        }
        //建立id索引
        if (!k.getIds().isEmpty()){
            for (Integer id : k.getIds()) {
                document.add(new StringField("id", id.toString(), Field.Store.YES));
            }
        }

        //指定索引目录
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath + File.separator + DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYY年MM月DD日)));
            //创建分词器
            IKAnalyzer ikAnalyzer = new IKAnalyzer();
            //添加配置信息
            IndexWriterConfig conf = new IndexWriterConfig(ikAnalyzer);
            IndexWriter indexWriter = new IndexWriter(directory, conf);
            //6 把文档交给IndexWriter
            indexWriter.addDocument(document);
            //7 提交
            indexWriter.commit();
            //8 关闭
            indexWriter.close();

            log.debug("索引信息-------->{}", document.getFields());
        } catch (Exception e) {
            log.error("创建索引失败" + e);
        }
    }
}
