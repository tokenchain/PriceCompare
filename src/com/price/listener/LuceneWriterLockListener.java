package com.price.listener;

import com.price.util.Lucene;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;


public class LuceneWriterLockListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ContextClosedEvent) {
            System.out.println("关闭Lucene资源！");

            //关闭Lucene资源
            Directory directory = Lucene.getFileDirectory();
            IndexWriter writer = Lucene.getWriter();
            if(writer != null) {
                try {
                    writer.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (directory != null) {
                try {
                    directory.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
            /*if (IndexWriter.isLocked(directory)) {
                IndexWriter.unlock(directory);
            }*/
        }
    }
}
