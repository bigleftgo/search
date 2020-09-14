package com.hwkj.search.controller;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.StringReader;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@SpringBootApplication
@Controller
public class LuceneSearch extends SpringBootServletInitializer {


        @RequestMapping("/LuceneSearchdddd")
        @ResponseBody
        public Map<String, String> search(ModelMap map, String q, ModelAndView mo) throws Exception {
            Map<String, String> m = new HashMap<String, String>();
            //查询文件路径
            String indexDir = "E:\\uploadFile\\2020-09-02\\index\\lucene2";
            //查询词汇
            Directory dir = FSDirectory.open(Paths.get(indexDir));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher is = new IndexSearcher(reader);
            SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//中文分词器
            //IKAnalyzer analyzer = new IKAnalyzer();//中文分词器
            QueryParser parser = new QueryParser("desc", analyzer);//万能检索
            Query query = parser.parse("\""+q+"\"");
            long start = System.currentTimeMillis();
            TopDocs hits = is.search(query, 10000);
            long end = System.currentTimeMillis();
            System.out.println("匹配 " + q + " ，总共花费" + (end - start) + "毫秒" + "查询到" + hits.totalHits + "个记录");
            QueryScorer scorer = new QueryScorer(query);
            Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
            SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
            Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
            highlighter.setTextFragmenter(fragmenter);
            for (ScoreDoc scoreDoc : hits.scoreDocs) {
                Document doc = is.doc(scoreDoc.doc);
                System.out.println(doc.get("name"));
                System.out.println(doc.get("id"));
                String id =doc.get("id");
                String desc = doc.get("desc");
                //Integer id = Integer.parseInt(doc.get("id"));
                String name= doc.get("name");
                if (desc != null) {
                    TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));
                    String content = highlighter.getBestFragment(tokenStream, desc);
                    System.out.println(content);
                    m.put(id, content);
                }
            }
            reader.close();
            map.addAttribute("map", m);
            return m;
        }


    @RequestMapping("/LuceneSearchId")
    @ResponseBody
    public Map<String, String> booleanQuerys(ModelMap map, String q, ModelAndView mo) throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        String indexDir = "E:\\uploadFile\\2020-09-02\\index\\lucene2";
        //查询词汇
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//中文分词器

        TermQuery nameQuery=new TermQuery(new Term("id", q));
        //TermQuery nameQuery=new TermQuery(new Term("name", q));
        BooleanClause clause2 = new BooleanClause(nameQuery, BooleanClause.Occur.SHOULD);

        BooleanQuery booleanQuery = new BooleanQuery.Builder().add(clause2).build();

        TopDocs topDocs = searcher.search(booleanQuery,1000);
        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scDoc : scoreDocs) {
        Document document = searcher.doc(scDoc.doc);
        String id = document.get("id");
        String name = document.get("name");
        String desc = document.get("desc");
        if (id != null) {

            System.out.println(desc);
            m.put(id, desc);
        }

    }
    reader.close();
    map.addAttribute("map", m);
    return m;
}

    @RequestMapping("/LuceneSearchName")
    @ResponseBody
    public Map<String, String> booleanQuery(ModelMap map, String q, ModelAndView mo) throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        String indexDir = "E:\\uploadFile\\2020-09-02\\index\\lucene2";
        //查询词汇
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//中文分词器

        TermQuery nameQuery=new TermQuery(new Term("id", "\""+q+"\""));
        //TermQuery nameQuery=new TermQuery(new Term("name", q));
        BooleanClause clause2 = new BooleanClause(nameQuery, BooleanClause.Occur.SHOULD);

        BooleanQuery booleanQuery = new BooleanQuery.Builder().add(clause2).build();

        TopDocs topDocs = searcher.search(booleanQuery,1000);
        System.out.println("共检索出 " + topDocs.totalHits + " 条记录");

        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scDoc : scoreDocs) {
            Document document = searcher.doc(scDoc.doc);
            String id = document.get("id");
            String name = document.get("name");
            String desc = document.get("desc");
            if (id != null) {

                System.out.println(desc);
                m.put(id, desc);
            }

        }
        reader.close();
        map.addAttribute("map", m);
        return m;
    }

    @RequestMapping("/LuceneSearchTest")
    @ResponseBody
    public Map<String, String> searchs(ModelMap map, String q, ModelAndView mo) throws Exception {
        Map<String, String> m = new HashMap<String, String>();
        //查询文件路径
        String indexDir = "E:\\uploadFile\\2020-09-02\\index\\lucene2";
        //查询词汇
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        //第一个参数表示：在哪个字段查询内容
        //第二参数：分词对象
        Analyzer analyzer=new StandardAnalyzer();
        //第二个参数表示符合条件的前n条记录
        FuzzyQuery query=new FuzzyQuery(new Term("desc",q),2);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<span style='color:#FF0000; background-color:#FFFF00'>", "</span>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));
        TopDocs hits =searcher.search(query,10000);
        if (hits != null && hits.totalHits > 0) {
                        ScoreDoc[] sDocs = hits.scoreDocs;
                        Document docMatched = null;
                        for (int j = 0; j < sDocs.length; j++)             {
                            System.out.println("Score doc " + j + " is " + sDocs[j].toString());
                             docMatched = searcher.doc(sDocs[j].doc);
                             TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(docMatched.get("desc")));
                             String id = docMatched.get("id");
                             String str = highlighter.getBestFragment(tokenStream,  docMatched.get("desc"));
                             System.out.println("Score doc " + j + " hightlight to: " + str);
                             m.put(id, str);
                             }
                     }
                    reader.close();
                    map.addAttribute("map", m);
                    return m;
    }










        public static void main(String[] args) {

            //SpringApplication.run(LuceneSearch.class, args);

            /*Map wendang = new HashMap();
            wendang.put("id1","aaaa");
            wendang.put("id2","bbbb");

            Map shujuku = new HashMap();
            shujuku.put("id1","abc");
            shujuku.put("id2","bbb");
            shujuku.put("id3","ccc");

            Set set = new HashSet<>();*/

            long start = System.currentTimeMillis();
            for (int i = 0; i <1000000000; i++) {
                for (int j = 0; j <100000000; j++) {
                    //System.out.println("总888888888888888888888888秒");
                }
            }
            long end = System.currentTimeMillis();

            System.out.println("总共花费" + (end - start) + "毫秒");


        }



    }