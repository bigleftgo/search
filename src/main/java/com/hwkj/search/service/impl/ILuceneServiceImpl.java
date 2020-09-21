package com.hwkj.search.service.impl;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.common.ErrorCode;
import com.hwkj.search.common.Result;
import com.hwkj.search.common.Results;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.utils.DocReadUtil;
import com.hwkj.search.vo.SearchVo;
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
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
                document.add(new TextField("desc", content, Field.Store.YES));
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
            for (String id : k.getIds()) {
                document.add(new StringField("id", id, Field.Store.YES));
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
    public Result<List<SearchVo>> search(List<Search> search) throws Exception {
        //打开索引目录
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        //索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        //索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        //创建查询解析器
        IKAnalyzer analyzer = new IKAnalyzer();
        if (!search.isEmpty()) {
            List<SearchVo> vos = new ArrayList<>();
            for (Search s : search) {
                //指定查询的域
                QueryParser queryParser = new QueryParser(s.getKey(), analyzer);
                //创建查询对象
                Query query;
                if (StringUtils.isEmpty(s.getValue())) {
                    //如果值为null，则查询所有
                    query = queryParser.parse("*:*");
                } else {
                    query = queryParser.parse(s.getValue());
                }
                //第二个参数返回多少条数据
                long start = System.currentTimeMillis();
                TopDocs docs = searcher.search(query, 20);
                long end = System.currentTimeMillis();
                log.info("匹配" + s.getValue() + "，共花费" + (end - start) + "毫秒" + "查询到" + docs.totalHits + "条数据");
                QueryScorer scorer = new QueryScorer(query);
                //第二个参数是返回页面的限制显示字数，默认为100
                Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 140);
                SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
                Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
                highlighter.setTextFragmenter(fragmenter);
                //获取结果集
                ScoreDoc[] scoreDocs = docs.scoreDocs;
                if (scoreDocs != null) {
                    for (int i = 0; i < scoreDocs.length; i++) {
                        ScoreDoc doc = scoreDocs[i];
                        SearchVo vo = new SearchVo();
                        //通过文档id读取id，获取查询到的文档标识，这个标识是lucene创建文档的时候为我们分配的唯一标识，与我们自己的id不一样
                        Document document = searcher.doc(doc.doc);
                        //获取高亮显示内容
                        String desc = highlighter.getBestFragment(analyzer, "desc", document.get("desc"));
                        //获取文件名称
                        String sjmc = document.get("sjmc");
                        //获取设计人
                        String sjr = document.get("sjr");
                        //获取文件id
                        String id = document.get("id");
                        //获取知识描述
                        String zsms = document.get("zsms");
                        vo.setDesc(desc);
                        vo.setSjr(sjr);
                        vo.setTitle(sjmc);
                        vo.setFileId(id);
                        vo.setK_des(zsms);
                        //插入关键字
                        vo.setSearch(s);
                        vos.add(vo);
                        log.info("search vo is {}", vo);
                    }
                }
            }
            return Results.newSuccessResult(vos);
        } else {
            return Results.newFailResult(ErrorCode.SYSTEM_ERROR, "查询参数为空，请正确输入查询参数");
        }
    }


}
