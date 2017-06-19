package com.price.dao;

import com.price.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component("productDAO")
public class ProductDAO {
    @Resource
    private SessionFactory sessionFactory;

    public Product getProductDetailById(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product;
    }

}
