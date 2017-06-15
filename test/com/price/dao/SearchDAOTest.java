package com.price.dao;

import com.price.model.Product;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@ContextConfiguration("/beans.xml")
public class SearchDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    SearchDAO searchDAO;

    @Test
    public void searchTest() throws Exception {
        List<Product> list = searchDAO.search("鼠标", 1, 100);
        list = searchDAO.search("显示器", 1, 100);

        Date start = new Date();
        list = searchDAO.search("风扇", 1, 100);
        for(Product o : list) {
            System.out.println(o);
        }
        Date end = new Date();
        System.out.println(list.size() + "|" + (end.getTime() - start.getTime()) + "ms");
    }

    @Test
    public void searchByIdTest() throws Exception {
        Product product = searchDAO.searchById(100391);
        System.out.println(product);
        product = searchDAO.searchById(12968819740l);
        System.out.println(product);
        Date start = new Date();
        product = searchDAO.searchById(12739963998l);
        System.out.println(product);
        Date end = new Date();
        System.out.println((end.getTime() - start.getTime()) + "ms");
    }

}
