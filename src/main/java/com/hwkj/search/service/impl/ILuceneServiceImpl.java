package com.hwkj.search.service.impl;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.utils.DocReadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 创建索引
     *
     * @param k
     */
    @Override
    public void createIndex(Knowledge k) {
        //创建文档对象
        Document document = new Document();
        //获取知识上传的文件内容
        List<String> contents = DocReadUtil.readWord(k.getPath());
        if (!contents.isEmpty()) {
            for (String content : contents) {
                //建立内容索引
                document.add(new TextField("desc", content, Field.Store.NO));
                log.info("上传文档内容{}", content);
            }
        }
        //根据名称建立名称所对应的值得索引
        if (!k.getNames().isEmpty()) {
            for (int i = 0; i < k.getNames().size(); i++) {
                document.add(new TextField(k.getNames().get(i), k.getValues().get(i), Field.Store.YES));
                log.info("索引key-------->{},索引value-------->{}", k.getNames().get(i), k.getValues().get(i));
            }
        }
        //建立id索引
        if (!k.getIds().isEmpty()) {
            for (Integer id : k.getIds()) {
                document.add(new StringField("id", id.toString(), Field.Store.YES));
            }
        }

        //指定索引目录
        try {
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            //添加配置信息
            IndexWriterConfig conf = new IndexWriterConfig(new IKAnalyzer());
            conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
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


    @Override
    public Map<String, Object> search(List<String> info, Search search) {
        try {
            Map<String, String> m = new HashMap<>();
            //打开索引目录
            Directory directory = FSDirectory.open(Paths.get(indexPath));
            //索引读取工具
            IndexReader reader = DirectoryReader.open(directory);
            //索引搜索工具
            IndexSearcher searcher = new IndexSearcher(reader);
            //创建查询解析器
            IKAnalyzer analyzer = new IKAnalyzer();
            for (String s : info) {
                //指定查询的域
                QueryParser queryParser = new QueryParser(search.getKey(),analyzer);
                //创建查询对象
                queryParser.parse(search.getValue());
            }
//            searcher.search()
        } catch (Exception e) {
            log.error("索引查询失败{}", e.getMessage());
        }
        return null;
    }


}
