package com.price.service;

import com.price.model.Product;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.List;

@ContextConfiguration("/beans.xml")
public class SearchServiceTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    SearchService searchService;

    @Test
    public void searchTest() throws Exception {
        int count = searchService.countKeyword("键盘");
        System.out.println("总数：" + count);
        List<Product> list = searchService.search("键盘",110, 10);
        for(Product p : list) {
            System.out.println(p);
        }
    }
}
