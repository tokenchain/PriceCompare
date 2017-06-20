package com.price.dao;

import com.opensymphony.xwork2.ModelDriven;
import com.price.dto.FollowDTO;
import com.price.model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("productDAO")
public class ProductDAO{
    @Resource
    private SessionFactory sessionFactory;


    public Product getProductDetailById(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, id);
        return product;
    }

    public List<Product> getProductDetailByIds(List<Long> ids) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from product where id in (:list)");
        q.setParameter("list", ids);
        List<Product> products = q.list();
        return products;
    }

}
