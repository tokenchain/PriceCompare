package com.price.service;

import com.price.dao.ProductDAO;
import com.price.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component("productService")
public class ProductService {
    @Resource
    private ProductDAO productDAO;

    public Product getProductDetailById(long id) {
        try {
            return productDAO.getProductDetailById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
