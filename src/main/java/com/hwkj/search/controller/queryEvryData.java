package com.hwkj.search.controller;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Lucene与数据库结合使用
 *
 * @author YipFun
 */
@CrossOrigin
@SpringBootApplication
@Controller


public class queryEvryData {

        private static final String driverClassName="com.mysql.jdbc.Driver";
        private static final String url="jdbc:mysql://127.0.0.1:3306/db_ssm?useSSL=false&characterEncoding=utf8";
        private static final String username="root";
        private static final String password="root";

        private static final Version version = Version.LUCENE_7_4_0;
        private Directory directory = null;
        private DirectoryReader ireader = null;
        private IndexWriter iwriter = null;
        //private IKAnalyzer analyzer;
        private Analyzer analyzer;
        private Connection conn;

        public queryEvryData() {
            directory = new RAMDirectory();
        }
        public IndexSearcher getSearcher(){
            try {
                if(ireader==null) {
                    ireader = DirectoryReader.open(directory);
                } else {
                    DirectoryReader tr = DirectoryReader.openIfChanged(ireader) ;
                    if(tr!=null) {
                        ireader.close();
                        ireader = tr;
                    }
                }
                return new IndexSearcher(ireader);
            } catch (CorruptIndexException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public Connection getConnection(){
            if(this.conn == null){
                try {
                    Class.forName(driverClassName);
                    conn = DriverManager.getConnection(url, username, password);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return conn;
        }
        private Analyzer getAnalyzer(){
            if(analyzer == null){
                return new StandardAnalyzer();
            }else{
                return analyzer;
            }
        }

        public ArrayList createIndex(String logind, String names, String phones, String ids){
            Connection conn = getConnection();
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            if(conn == null){
                return null;
            }
            String sql = "select * from t_user t where 1=1";
            if(!logind.isEmpty()){
                sql= sql  +  " and t.login  like  "+"'%"+logind+"%'";
            }
            if(!names.isEmpty()){
                sql= sql  +  " and t.user_name  like  "+"'%"+names+"%'";
            }
            if(!phones.isEmpty()){
                sql= sql  +  " and t.user_phone  like  "+"'%"+phones+"%'";
            }
            if(!ids.isEmpty()){
               // sql= sql  +  " and t.id  like  "+"%"+ids+"%";
            }

            try {
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                IndexWriterConfig iwConfig=new IndexWriterConfig(getAnalyzer());
                iwConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
                iwriter = new IndexWriter(directory,iwConfig);
                while(rs.next()){
                    Integer id = rs.getInt(1);
                    String login = rs.getString(2);
                    String name= rs.getString(3);
                    String phone=rs.getString(4);
                    String email = rs.getString(5);
                    String p23= rs.getString(11);

                    Document doc = new Document();

                    doc.add(new TextField("id", id+"", Field.Store.YES));//1
                    doc.add(new TextField("login", login+"",Field.Store.YES));//2
                    doc.add(new TextField("name", name+"",Field.Store.YES));//4
                    doc.add(new TextField("phone", phone+"",Field.Store.YES));//5
                    doc.add(new TextField("email", email+"",Field.Store.YES));//6
                    doc.add(new TextField("p23", p23+"",Field.Store.YES));//11

                    iwriter.addDocument(doc);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                try {
                    if(iwriter != null)
                        iwriter.close();
                    rs.close();
                    pstmt.close();
                    if(!conn.isClosed()){
                        conn.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

        public ArrayList<String> searchByTerm(String field, String keyword, int num) throws Exception{
            ArrayList<String> list=new ArrayList<String>();
            IndexSearcher isearcher = getSearcher();
            Analyzer analyzer =  getAnalyzer();
            //使用QueryParser查询分析器构造Query对象
            QueryParser qp = new QueryParser(field,analyzer);
            //这句所起效果？
            // qp.setDefaultOperator(QueryParser.OR_OPERATOR);
            qp.setDefaultOperator(QueryParser.AND_OPERATOR);
            try {
                Query query = qp.parse(keyword);
                ScoreDoc[] hits;

                //注意searcher的几个方法
                hits = isearcher.search(query, num).scoreDocs;
                // assertEquals(1, hits.length);
                /*System.out.println("the names is =");*/
                if(hits.length>0){
                    for (int i = 0; i < hits.length; i++) {
                        Document doc = isearcher.doc(hits[i].doc);
                        if(doc.get("p23")!=""){
                            list.add(doc.get("p23"));
                        }
                        /*System.out.print(doc.get("id")+" ");
                        System.out.println(doc.get("name")+" ");*/
                    }
                }else{
                    System.out.println("无查询数据！");
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return list;
        }

        /*public static void main(String[] args) throws Exception {
            queryEvryData ld = new queryEvryData();
            ld.createIndex(login name,phone,id);
            ld.searchByTerm("name", "王", 100);
        }*/

    @RequestMapping("/LuceneSearch")
    @ResponseBody
    public Map<Integer, String> search(ModelMap map, String login,String name,String id,String phone, String q,ModelAndView mo) throws Exception {
        queryEvryData ld = new queryEvryData();
        ArrayList listData=ld.createIndex(login,name,phone,id);
        Map<Integer, String> m = new HashMap<Integer, String>();

        String indexDir = "D:\\LuceneDemo\\lucene4";
        //查询词汇
        q = "篮球";
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();//中文分词器
        QueryParser parser = new QueryParser("desc", analyzer);
        Query query = parser.parse(q);
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);
        long end = System.currentTimeMillis();
        //System.out.println("匹配 " + q + " ，总共花费" + (end - start) + "毫秒" + "查询到" + hits.totalHits + "个记录");
        QueryScorer scorer = new QueryScorer(query);
        Fragmenter fragmenter = new SimpleSpanFragmenter(scorer);
        SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
        Highlighter highlighter = new Highlighter(simpleHTMLFormatter, scorer);
        highlighter.setTextFragmenter(fragmenter);
        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("name"));
            System.out.println(doc.get("id"));
            //Integer id =doc.get("id");
            String desc = doc.get("desc");
            Integer idd = Integer.parseInt(doc.get("id"));
            if (desc != null) {

                TokenStream tokenStream = analyzer.tokenStream("desc", new StringReader(desc));
                String content = highlighter.getBestFragment(tokenStream, desc);
               /* System.out.println(content);*/
                m.put(idd, content);

            }

        }

        reader.close();
        map.addAttribute("map", m);
        return m;
    }
}
