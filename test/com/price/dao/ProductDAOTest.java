package com.price.dao;

import com.price.model.Product;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.sql.SQLException;

@ContextConfiguration("/beans.xml")
public class ProductDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private ProductDAO productDAO;

    @Test
    public void getProductDetailByIdTest() throws SQLException {
        Product p = productDAO.getProductDetailById(104256);
        System.out.println(p);
    }
}
