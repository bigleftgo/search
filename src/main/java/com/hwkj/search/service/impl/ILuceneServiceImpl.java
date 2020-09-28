package com.hwkj.search.service.impl;

import com.hwkj.search.bean.Knowledge;
import com.hwkj.search.bean.Search;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.utils.DateUtil;
import com.hwkj.search.utils.DocReadUtil;
import com.hwkj.search.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.xml.builders.RangeQueryBuilder;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.poi.util.LongField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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
    public void createIndex(List<Knowledge> k) throws IOException {
        List<Document> list = new ArrayList<>();
        for (Knowledge knowledge : k) {
            //创建文档对象
            Document document = new Document();
            //获取知识上传的文件内容
            if (!StringUtils.isEmpty(knowledge.getPath())) {
                List<String> contents = DocReadUtil.readWord(knowledge.getPath());
                if (!contents.isEmpty()) {
                    for (String content : contents) {
                        //建立内容索引
                        document.add(new TextField("desc", content, Field.Store.YES));
                        log.info("上传文档内容{}", content);
                    }
                }
            }
            //根据名称建立名称所对应的值得索引
            if (!knowledge.getNames().isEmpty()) {
                for (int i = 0; i < knowledge.getNames().size(); i++) {
                    if (knowledge.getNames().get(i).equals("zzmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("gysjrq")) {
                        Date date = DateUtil.parseStrToDate(knowledge.getValues().get(i), "yyyy-MM-dd");
                        document.add(new LongPoint(knowledge.getNames().get(i), date.getTime()));
                        document.add(new StoredField(knowledge.getNames().get(i), date.getTime()));
                    } else if (knowledge.getNames().get(i).equals("dzsjrq")) {
                        Date date = DateUtil.parseStrToDate(knowledge.getValues().get(i), "yyyy-MM-dd");
                        document.add(new LongPoint(knowledge.getNames().get(i), date.getTime()));
                        document.add(new StoredField(knowledge.getNames().get(i), date.getTime()));
                    } else if (knowledge.getNames().get(i).equals("sgsjrq")) {
                        Date date = DateUtil.parseStrToDate(knowledge.getValues().get(i), "yyyy-MM-dd");
                        document.add(new LongPoint(knowledge.getNames().get(i), date.getTime()));
                        document.add(new StoredField(knowledge.getNames().get(i), date.getTime()));
                    } else {
                        document.add(new StringField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    }
                    log.info("索引key-------->{},索引value-------->{}", knowledge.getNames().get(i), knowledge.getValues().get(i));
                }
            }
            //建立id索引
            if (!knowledge.getIds().isEmpty()) {
                for (String id : knowledge.getIds()) {
                    document.add(new StringField("id", id, Field.Store.YES));
                    log.info("id---------->{}", id);

                }
            }
            //建立tag
            if (!StringUtils.isEmpty(knowledge.getZsbq())) {
                String[] tags = knowledge.getZsbq().split(",");
                for (String tag : tags) {
                    document.add(new StringField("tag", tag, Field.Store.YES));
                    log.info("tag---------->{}", tag);
                }
            }
            list.add(document);
        }
        //指定索引目录

        Directory directory = FSDirectory.open(Paths.get(indexPath));
        //添加配置信息
        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
        conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        //6 把文档交给IndexWriter
        indexWriter.addDocuments(list);
        //7 提交
        indexWriter.commit();
        //8 关闭
        indexWriter.close();
        log.info("索引信息：{}", list.toString());
    }

    /**
     * 查询索引
     * @param search 查询对象
     * @return
     * @throws Exception
     */
    @Override
    public List<SearchVo> search(List<Search> search) throws Exception {
        //打开索引目录
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        //索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        //索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        //创建查询解析器
//        IKAnalyzer analyzer = new IKAnalyzer();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        if (!search.isEmpty()) {
            BooleanQuery.Builder builder = new BooleanQuery.Builder();
            List<SearchVo> vos = new ArrayList<>();
            //创建组合查询对象
            for (Search s : search) {
                //指定查询的域
                Query query1;
                Query query2;
                Query query3;
                Query query4;
                Query query5;
                //如果状态为should，那么使用“或”关系拼接
                if (s.getStatus().equalsIgnoreCase("should")) {
                    if (s.getKey().equals("sjrq")) {
                        Date start = DateUtil.parseStrToDate(s.getStartTime(), "yyyy-MM-dd");
                        Date end = DateUtil.parseStrToDate(s.getEndTime(), "yyyy-MM-dd");
                        query3 = LongPoint.newRangeQuery("gysjrq", start.getTime(), end.getTime());
                        query4 = LongPoint.newRangeQuery("dzsjrq", start.getTime(), end.getTime());
                        query5 = LongPoint.newRangeQuery("sgsjrq", start.getTime(), end.getTime());
                        builder.add(query3, BooleanClause.Occur.SHOULD);
                        builder.add(query4, BooleanClause.Occur.SHOULD);
                        builder.add(query5, BooleanClause.Occur.SHOULD);
                    } else {
                        //创建查询对象
                        query1 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query1, BooleanClause.Occur.SHOULD);
                    }
                }
                //如果状态为must，那么用“且”关系拼接
                if (s.getStatus().equalsIgnoreCase("must")) {
                    if (s.getKey().equalsIgnoreCase("zzmc")) {
                        query2 = new FuzzyQuery(new Term(s.getKey(), s.getValue()));
                        builder.add(query2, BooleanClause.Occur.MUST);
                    } else {
                        query1 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query1, BooleanClause.Occur.MUST);
                    }
                }
            }
            //第二个参数返回多少条数据
            long start = System.currentTimeMillis();
//            Sort sort = new Sort();
//            sort.setSort(new SortField("sjrq", SortField.Type.LONG,true));
            TopDocs docs = searcher.search(builder.build(), 20);
            long end = System.currentTimeMillis();
            log.info("匹配" + search.toString() + "，共花费" + (end - start) + "毫秒" + "查询到" + docs.totalHits + "条数据");
            QueryScorer scorer = new QueryScorer(builder.build());
            //第二个参数是返回页面的限制显示字数，默认为100
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer, 150);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
            highlighter.setTextFragmenter(fragmenter);
            //获取结果集
            ScoreDoc[] scoreDocs = docs.scoreDocs;
            if (scoreDocs != null) {
                for (int i = 0; i < scoreDocs.length; i++) {
                    SearchVo vo = new SearchVo();
                    //通过文档id读取id，获取查询到的文档标识，这个标识是lucene创建文档的时候为我们分配的唯一标识，与我们自己的id不一样
                    Document document = searcher.doc(scoreDocs[i].doc);
                    //获取高亮显示内容
                    if (document.get("desc") != null) {
                        String desc = highlighter.getBestFragment(analyzer, "desc", document.get("desc"));
                        vo.setDesc(desc);
                    }
                    //插入设计人
                    vo.setGysjsjr(document.get("gysjsjr"));
                    vo.setDzsjsjr(document.get("dzsjsjr"));
                    vo.setSgsjsjr(document.get("sgsjsjr"));
                    //插入设计名称
                    vo.setGysjsjmc(document.get("gysjsjmc"));
                    vo.setDzsjsjmc(document.get("dzsjsjmc"));
                    vo.setSgsjsjmc(document.get("sgsjsjmc"));
                    vo.setFileId(document.get("id"));
                    vo.setK_des(document.get("zsms"));

                    //插入设计单位
                    vo.setGysjsjdw(document.get("gysjsjdw"));
                    vo.setDzsjsjdw(document.get("dzsjsjdw"));
                    vo.setSgsjsjdw(document.get("sgsjsjdw"));
                    vo.setSearch(document.get("zsbq"));
                    //插入设计时间
                    vo.setGysjrq(document.get("gysjrq"));
                    vo.setDzsjrq(document.get("dzsjrq"));
                    vo.setSgsjrq(document.get("sgsjrq"));
                    //插入设计类型
                    vo.setGysjlx(document.get("gysjlx"));
                    vo.setDzsjlx(document.get("dzsjlx"));
                    vo.setSgsjlx(document.get("sgsjlx"));

                    vos.add(vo);
                }
            }
            log.info("search vo is {}", vos);
            return vos;
        } else {
            return null;
        }
    }
}



