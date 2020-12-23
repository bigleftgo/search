package com.hwkj.search.service.impl;

import com.hwkj.search.bean.*;
import com.hwkj.search.service.ILuceneService;
import com.hwkj.search.utils.DocReadUtil;
import com.hwkj.search.vo.HwkjSearchVo;
import com.hwkj.search.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
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
    @Value("${file.index-hwkj-path}")
    private String indexHwkjPath;


    /**
     * 创建索引
     *
     * @param k
     */
    @Override
    public void createIndex(List<Knowledge> k) throws IOException {
        List<Document> list = new ArrayList<>();
        for (int j = 0; j < k.size(); j++) {
            Knowledge knowledge = k.get(j);
            //创建文档对象
            Document document = new Document();
            //获取知识上传的文件内容
            StringBuilder b = new StringBuilder();
            //将所有的value拼接到一起，放到desc种
            for (String value : knowledge.getValues()) {
                b.append(value);
            }
            if (!StringUtils.isEmpty(knowledge.getPath())) {
                List<String> contents = DocReadUtil.readWord(knowledge.getPath());
                if (!contents.isEmpty()) {
                    for (String content : contents) {
                        //建立内容索引
                        document.add(new TextField("desc", content + b.toString(), Field.Store.YES));
                        log.info("上传文档内容{}", content);
                    }
                }
            }
            //根据名称建立名称所对应的值得索引
            if (!knowledge.getNames().isEmpty()) {
                for (int i = 0; i < knowledge.getNames().size(); i++) {
                    if (knowledge.getNames().get(i).equals("zzmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("gysjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("dzsjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("sgsjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("yjmxmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("bzgfmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
//                    }
//                    else if (knowledge.getNames().get(i).equals("desc")) {
//                        //如果desc有，说明传入的为
//                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("desc")) {
                        StringBuilder builder = new StringBuilder();
                        for (int y = 0; y < knowledge.getValues().size(); y++) {
                            builder.append(knowledge.getValues().get(y));
                        }
                        document.add(new TextField("desc", builder.toString(), Field.Store.YES));
                        log.info("desc---------->{}", builder.toString());
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
     *
     * @param searchParam 查询对象
     * @return
     * @throws Exception
     */
    @Override
    public List<SearchVo> search(SearchParam searchParam) throws Exception {
        //打开索引目录
        Directory directory = FSDirectory.open(Paths.get(indexHwkjPath));
        //索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        //索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        //创建查询解析器
//        IKAnalyzer analyzer = new IKAnalyzer();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        List<SearchVo> vos = new ArrayList<>();

        if (!searchParam.getSearche().isEmpty()) {
            //创建组合查询对象
            for (Search s : searchParam.getSearche()) {
                //指定查询的域
                Query query1;
                Query query2;
                Query query6;
                //如果状态为should，那么使用“或”关系拼接
                if (s.getStatus().equalsIgnoreCase("should")) {
                    if (s.getKey().equals("desc")) {
                        query6 = new QueryParser("desc", analyzer).parse("\"" + s.getValue() + "\"");
//                        query6 = new QueryParser("desc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else if (s.getKey().equals("bzgfmc")) {
//                        query6 = new QueryParser("bzgfmc", analyzer).parse("\"" + s.getValue() + "\"");
                        query6 = new QueryParser("bzgfmc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else if (s.getKey().equals("gysjsjmc")) {
//                        query6 = new QueryParser("gysjsjmc", analyzer).parse("\"" + s.getValue() + "\"");
                        query6 = new QueryParser("gysjsjmc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else if (s.getKey().equals("dzsjsjmc")) {
//                        query6 = new QueryParser("dzsjsjmc", analyzer).parse("\"" + s.getValue() + "\"");
                        query6 = new QueryParser("dzsjsjmc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else if (s.getKey().equals("sgsjsjmc")) {
//                        query6 = new QueryParser("sgsjsjmc", analyzer).parse("\"" + s.getValue() + "\"");
                        query6 = new QueryParser("sgsjsjmc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else if (s.getKey().equals("yjmxmc")) {
//                        query6 = new QueryParser("yjmxmc", analyzer).parse("\"" + s.getValue() + "\"");
                        query6 = new QueryParser("yjmxmc", analyzer).parse(s.getValue());
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else {
                        //创建查询对象
                        query1 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query1, BooleanClause.Occur.SHOULD);
                    }
                }
                //如果状态为must，那么用“且”关系拼接
                if (s.getStatus().equalsIgnoreCase("must")) {
                    if (s.getKey().equals("desc")) {
                        query6 = new QueryParser("desc", analyzer).parse("\"" + s.getValue() + "\"");
                        builder.add(query6, BooleanClause.Occur.MUST);
                    } else {
                        query2 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query2, BooleanClause.Occur.MUST);
                    }
                }
            }
            //第二个参数返回多少条数据
            long start = System.currentTimeMillis();
            TopDocs docs = searcher.search(builder.build(), Integer.MAX_VALUE);
            long end = System.currentTimeMillis();
            log.info("匹配" + searchParam.getSearche().toString() + "，共花费" + (end - start) + "毫秒" + "查询到" + docs.totalHits + "条数据");
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
                        if (!StringUtils.isEmpty(desc)) {
                            vo.setDesc(desc);
                        } else {
                            vo.setDesc(document.get("desc"));
                        }
                    }
                    //插入设计人
                    vo.setGysjsjr(document.get("gysjsjr"));
                    vo.setDzsjsjr(document.get("dzsjsjr"));
                    vo.setSgsjsjr(document.get("sgsjsjr"));
                    //插入设计名称
                    if (document.get("gysjsjmc") != null) {
                        String gysjsjmc = highlighter.getBestFragment(analyzer, "gysjsjmc", document.get("gysjsjmc"));
                        if (!StringUtils.isEmpty(gysjsjmc)) {
                            vo.setGysjsjmc(gysjsjmc);
                        } else {
                            vo.setGysjsjmc(document.get("gysjsjmc"));
                        }
                    }
                    if (document.get("dzsjsjmc") != null) {
                        String dzsjsjmc = highlighter.getBestFragment(analyzer, "dzsjsjmc", document.get("dzsjsjmc"));
                        if (!StringUtils.isEmpty(dzsjsjmc)) {
                            vo.setDzsjsjmc(dzsjsjmc);
                        } else {
                            vo.setDzsjsjmc(document.get("dzsjsjmc"));
                        }
                    }
                    if (document.get("sgsjsjmc") != null) {
                        String sgsjsjmc = highlighter.getBestFragment(analyzer, "sgsjsjmc", document.get("sgsjsjmc"));
                        if (!StringUtils.isEmpty(sgsjsjmc)) {
                            vo.setSgsjsjmc(sgsjsjmc);
                        } else {
                            vo.setSgsjsjmc(document.get("sgsjsjmc"));
                        }
                    }
                    vo.setFileId(document.get("id"));
                    vo.setK_des(document.get("zsms"));
                    vo.setJsdl(document.get("jsdl"));
                    //预警模型名称
                    if (document.get("yjmxmc") != null) {
                        String yjmxmc = highlighter.getBestFragment(analyzer, "yjmxmc", document.get("yjmxmc"));
                        if (!StringUtils.isEmpty(yjmxmc)) {
                            vo.setYjmxmc(yjmxmc);
                        } else {
                            vo.setYjmxmc(document.get("yjmxmc"));
                        }
                    }
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
                    //插入措施类型
                    vo.setCslx(document.get("cslx"));

                    //预警模型
                    vo.setYjmxsjlx(document.get("yjmxsjlx"));
                    vo.setYjmxnr(document.get("yjmxnr"));
                    //标准规范
                    if (document.get("bzgfmc") != null) {
                        String bzgfmc = highlighter.getBestFragment(analyzer, "bzgfmc", document.get("bzgfmc"));
                        if (!StringUtils.isEmpty(bzgfmc)) {
                            vo.setBzgfmc(bzgfmc);
                        } else {
                            vo.setBzgfmc(document.get("bzgfmc"));
                        }
                    }
                    vo.setBzgfsjlx(document.get("bzgfsjlx"));
                    vo.setBzgfms(document.get("bzgfms"));
                    vos.add(vo);
                }
            }
            log.info("search vo is {}", vos);
        }
        return vos;
    }

    /**
     * 更新索引
     *
     * @param k
     * @throws IOException
     */
    @Override
    public void updateIndex(ProUpKonwledge k) throws IOException {
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        Document doc = new Document();
        for (UpKonwledge konwledge : k.getList()) {
            if (konwledge.getKey().equals("tag")) {
                String[] split = konwledge.getNewVal().split(",");
                for (String s : split) {
                    doc.add(new StringField(konwledge.getKey(), s, Field.Store.YES));
                }
            }
            doc.add(new StringField(konwledge.getKey(), konwledge.getNewVal(), Field.Store.YES));
            log.info("修改索引key----------->{}", konwledge.getKey());
        }
        if (!StringUtils.isEmpty(k.getPath())) {
            List<String> pathList = new ArrayList<>();
            pathList.add(k.getPath());
            List<String> contents = DocReadUtil.readWord(pathList);
            if (!contents.isEmpty()) {
                for (String content : contents) {
                    //建立内容索引
                    doc.add(new TextField("desc", content, Field.Store.YES));
                    log.info("修改文档内容文档内容{}", content);
                }
            }
        }
        long l = indexWriter.updateDocument(new Term("id", k.getId()), doc);
        log.info("更新了" + l + "条");
        indexWriter.commit();
        indexWriter.close();
    }

    /**
     * 删除索引
     *
     * @param id 文档id
     * @throws Exception
     */
    @Override
    public void deleteIndex(String id) throws Exception {
        Directory directory = FSDirectory.open(Paths.get(indexPath));
        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
        IndexWriter indexWriter = new IndexWriter(directory, conf);
        indexWriter.deleteDocuments(new Term("id", id));
        indexWriter.commit();
        indexWriter.close();
    }

    @Override
    public List<HwkjSearchVo> hwkjSearch(SearchParam searchParam) throws IOException, ParseException, InvalidTokenOffsetsException {
        //打开索引目录
        Directory directory = FSDirectory.open(Paths.get(indexHwkjPath));
        //索引读取工具
        IndexReader reader = DirectoryReader.open(directory);
        //索引搜索工具
        IndexSearcher searcher = new IndexSearcher(reader);
        //创建查询解析器
//        IKAnalyzer analyzer = new IKAnalyzer();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        List<HwkjSearchVo> vos = new ArrayList<>();
        if (!searchParam.getSearche().isEmpty()) {
            for (Search s : searchParam.getSearche()) {
                //指定查询的域
                Query query1;
                Query query2;
                Query query6;
                //如果状态为should，那么使用“或”关系拼接
                if (s.getStatus().equalsIgnoreCase("should")) {
                    if (s.getKey().equals("desc")) {
                        query6 = new QueryParser("desc", analyzer).parse("\"" + s.getValue() + "\"");
                        builder.add(query6, BooleanClause.Occur.SHOULD);
                    } else {
                        //创建查询对象
                        query1 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query1, BooleanClause.Occur.SHOULD);
                    }
                }
                //如果状态为must，那么用“且”关系拼接
                if (s.getStatus().equalsIgnoreCase("must")) {
                    if (s.getKey().equals("desc")) {
                        query6 = new QueryParser("desc", analyzer).parse("\"" + s.getValue() + "\"");
                        builder.add(query6, BooleanClause.Occur.MUST);
                    } else {
                        query2 = new TermQuery(new Term(s.getKey(), s.getValue()));
                        //将每一个参数都放入到组合查询中
                        builder.add(query2, BooleanClause.Occur.MUST);
                    }
                }
            }

            //第二个参数返回多少条数据
            long start = System.currentTimeMillis();
            TopDocs docs = searcher.search(builder.build(), Integer.MAX_VALUE);
            long end = System.currentTimeMillis();
            log.info("匹配" + searchParam.getSearche().toString() + "，共花费" + (end - start) + "毫秒" + "查询到" + docs.totalHits + "条数据");
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
                    HwkjSearchVo vo = new HwkjSearchVo();
                    Document document = searcher.doc(scoreDocs[i].doc);
                    //获取高亮显示内容
                    if (document.get("desc") != null) {
                        String desc = highlighter.getBestFragment(analyzer, "desc", document.get("desc"));
                        vo.setDesc(desc);
                        if (!StringUtils.isEmpty(desc)) {
                            vo.setDesc(desc);
                        } else {
                            vo.setDesc(document.get("desc"));
                        }
                    }

                    vo.setWdmc(document.get("wdmc"));
                    vo.setMs(document.get("ms"));
                    vo.setZsbq(document.get("zsbq"));
                    vo.setBxbm(document.get("bxbm"));
                    vo.setBxr(document.get("bxr"));
                    vo.setBxrq(document.get("bxrq"));
                    vo.setXmmc(document.get("xmmc"));
                    vo.setSslx(document.get("sslx"));
                    vo.setJsdl(document.get("jsdl"));
                    vo.setWdlx(document.get("wdlx"));
                    vo.setFileId(document.get("id"));
                    vos.add(vo);
                    log.info("索引vo-------{}",vo);
                }
            }
        }
        return vos;
    }

    @Override
    public void hwkjCreatIndex(List<Knowledge> k) throws IOException {
        List<Document> list = new ArrayList<>();
        for (int j = 0; j < k.size(); j++) {
            Knowledge knowledge = k.get(j);
            //创建文档对象
            Document document = new Document();
            //获取知识上传的文件内容
            StringBuilder b = new StringBuilder();
            //将所有的value拼接到一起，放到desc种
            for (String value : knowledge.getValues()) {
                b.append(value);
            }
            if (!StringUtils.isEmpty(knowledge.getPath())) {
                List<String> contents = DocReadUtil.readWord(knowledge.getPath());
                if (!contents.isEmpty()) {
                    for (String content : contents) {
                        //建立内容索引
                        document.add(new TextField("desc", content + b.toString(), Field.Store.YES));
                        log.info("上传文档内容{}", content);
                    }
                }
            }
            //根据名称建立名称所对应的值得索引
            if (!knowledge.getNames().isEmpty()) {
                for (int i = 0; i < knowledge.getNames().size(); i++) {
                    if (knowledge.getNames().get(i).equals("zzmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("gysjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("dzsjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("sgsjsjmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("yjmxmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("bzgfmc")) {
                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
//                    }
//                    else if (knowledge.getNames().get(i).equals("desc")) {
//                        //如果desc有，说明传入的为
//                        document.add(new TextField(knowledge.getNames().get(i), knowledge.getValues().get(i), Field.Store.YES));
                    } else if (knowledge.getNames().get(i).equals("desc")) {
                        StringBuilder builder = new StringBuilder();
                        for (int y = 0; y < knowledge.getValues().size(); y++) {
                            builder.append(knowledge.getValues().get(y));
                        }
                        document.add(new TextField("desc", builder.toString(), Field.Store.YES));
                        log.info("desc---------->{}", builder.toString());
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

        Directory directory = FSDirectory.open(Paths.get(indexHwkjPath));
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
}



