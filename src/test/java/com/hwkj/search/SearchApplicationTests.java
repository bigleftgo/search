//package com.hwkj.search;
//
//import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.document.Field;
//import org.apache.lucene.document.StringField;
//import org.apache.lucene.index.*;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//import java.nio.file.Paths;
//
//@SpringBootTest
//class SearchApplicationTests {
//
//    @Value("${file.index-path}")
//    private String indexPath;
//
//    /**
//     * 更新索引初级
//     *
//     * @throws IOException
//     */
//    @Test
//    void update() throws IOException {
//        Directory directory = FSDirectory.open(Paths.get(indexPath));
//        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
//        IndexWriter indexWriter = new IndexWriter(directory, conf);
//        Document document = new Document();
//
////        String updatePath = "\\2020-09-28\\1c1135e24219492b9f6b17f35867d591.doc";
////        ArrayList<String> list = new ArrayList<>();
////        list.add(updatePath);
////        List<String> word = DocReadUtil.readWord(list);
////        for (String s : word) {
////            document.add(new TextField("desc", s, Field.Store.YES));
////        }
//        document.add(new StringField("gysjsjmc", "123123123", Field.Store.YES));
////        document.add(new TextField("zzmc", "随便写的测试内容", Field.Store.YES));
//        indexWriter.updateDocument(new Term("gysjsjmc", "工艺设计111sss"), document);
//        indexWriter.commit();
//        indexWriter.close();
//    }
//
//    @Test
//    void undelete() throws IOException {
//        Directory directory = FSDirectory.open(Paths.get(indexPath));
//        IndexReader reader = DirectoryReader.open(directory);
//        System.out.println(reader.numDocs());
//    }
//
//    @Test
//    void deleteAll() throws IOException {
//        Directory directory = FSDirectory.open(Paths.get(indexPath));
//        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
//        IndexWriter indexWriter = new IndexWriter(directory, conf);
//        indexWriter.forceMergeDeletes();
//        indexWriter.commit();
//        indexWriter.close();
//        System.out.println(indexWriter.numDocs());
//    }
//
//    @Test
//    void deleteTerm() throws Exception{
//        Directory directory = FSDirectory.open(Paths.get(indexPath));
//        IndexWriterConfig conf = new IndexWriterConfig(new SmartChineseAnalyzer());
//        IndexWriter indexWriter = new IndexWriter(directory, conf);
//        indexWriter.deleteDocuments(new Term("gy","工艺"));
//        indexWriter.commit();
//        indexWriter.close();
//    }
//
//}
