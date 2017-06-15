package com.price.util;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Lucene {
    private static Lucene lucene = new Lucene();
    private static Analyzer analyzer;
    private static File file;
    private static Directory fileDirectory;
    private Directory RAMDirectory;
    private static Connection conn;
    private static Statement stmt;
    private static DirectoryReader reader;
    private static IndexSearcher searcher;
    private static IndexWriterConfig iwc;
    private static IndexWriter writer;
    //默认查询数量
    private static int totalCount;

    public Lucene() {
        try {
            analyzer = new SmartChineseAnalyzer(Version.LUCENE_4_9);
            file = new File("d:\\Lucene\\index");
            fileDirectory = FSDirectory.open(file);

            RAMDirectory = new RAMDirectory(fileDirectory,new IOContext());

            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jingdong","root","root");
            stmt = conn.createStatement();

            if (IndexWriter.isLocked(fileDirectory)) {
                IndexWriter.unlock(fileDirectory);
            }
            iwc = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            writer = new IndexWriter(fileDirectory, iwc);

            reader = DirectoryReader.open(RAMDirectory);
            searcher = new IndexSearcher(reader);

            totalCount = 1000;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Lucene getLucene() {
        return lucene;
    }

    public static void main(String[] args) throws Exception{
        Lucene lucene = Lucene.getLucene();
        Date start = new Date();

        //lucene.createIndex();
        System.out.println(lucene.search("编织").size());

        Date end = new Date();
        System.out.println("used:" + (end.getTime() - start.getTime()) + "ms.");

        start = new Date();
        System.out.println(lucene.search("编织线宏编程").size());
        end = new Date();
        System.out.println("used:" + (end.getTime() - start.getTime()) + "ms.");

        start = new Date();
        System.out.println(lucene.search("可自定义宏背光").size());
        end = new Date();
        System.out.println("used:" + (end.getTime() - start.getTime()) + "ms.");
    }

    public void createIndex() throws Exception {
        ResultSet rs = stmt.executeQuery("SELECT id, name FROM jingdong.product");
        Document doc;
        String text;
        while (rs.next()) {
            System.out.println(rs.getString(1));
            //存储
            doc = new Document();
            text = rs.getString(2);
            doc.add(new Field("name", text, TextField.TYPE_NOT_STORED));
            doc.add(new Field("id", rs.getString(1), TextField.TYPE_STORED));
            writer.addDocument(doc);
        }
        writer.close();
    }

    public ArrayList<Long> search(String keyword) throws Exception {
        ArrayList<Long> list = null;

        Date start = new Date();

        QueryParser parser = new QueryParser(Version.LUCENE_4_9, "name", analyzer);
        Query query = parser.parse(keyword);
        ScoreDoc[] hits = searcher.search(query, totalCount).scoreDocs;
        list = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document hitDoc = searcher.doc(hits[i].doc);
            list.add(new Long(hitDoc.get("id")));
        }
        Date end = new Date();
        System.out.println("Lucene used:" + (end.getTime() - start.getTime()) + "ms.");
        return list;
    }

    public int countKeyword(String keyword) throws Exception {
        QueryParser parser = new QueryParser(Version.LUCENE_4_9, "name", analyzer);
        Query query = parser.parse(keyword);
        ScoreDoc[] hits = searcher.search(query, totalCount).scoreDocs;
        return hits.length;
    }

    public static Directory getFileDirectory() {
        return fileDirectory;
    }

    public Directory getRAMDirectory() {
        return RAMDirectory;
    }

    public static IndexWriter getWriter() {
        return writer;
    }
}
