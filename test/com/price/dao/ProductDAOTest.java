package com.price.dao;

import com.price.model.Product;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration("/beans.xml")
public class ProductDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private ProductDAO productDAO;

    @Test
    public void getProductDetailByIdTest() throws SQLException {
        Product p = productDAO.getProductDetailById(104256);
        System.out.println(p);
    }

    @Test
    public void getProductDetailByIdsTest() throws SQLException {
        List<Long> ids = new ArrayList<>();
        ids.add(104256l);
        ids.add(115741l);
        List<Product> list = productDAO.getProductDetailByIds(ids);
        for(Product p : list) {
            System.out.println(p.getName() + "|" + p.getId() + "|" + p.getLast_price());
        }
    }
}
