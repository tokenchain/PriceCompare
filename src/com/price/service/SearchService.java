package com.price.service;

import com.price.dao.SearchDAO;
import com.price.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("searchService")
public class SearchService {
    @Resource
    SearchDAO searchDAO;

    public List<Product> search(String keyword, int page, int size) {
        if(keyword == null || keyword.trim().equals("")) {
            return new ArrayList<>();
        }
        List<Product> products;
        keyword = pretreatment(keyword);
        //先搜索是否可对应为Id对应
        Product product = searchById(keyword);
        if(product != null) {
            products = new ArrayList<>();
            products.add(product);
            return products;
        }
        try {
            products = searchDAO.search(keyword, page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return products;

    }

    public Product searchById(String id) {
        if(id.matches("\\d{6,15}")) {
            //可被解析为id
            try {
                Product product = searchDAO.searchById(new Long(id));
                if(product != null) {
                    return product;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public int countKeyword(String keyword) {
        try{
            return searchDAO.countKeyword(keyword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private String pretreatment(String intput) {
        return intput.trim();
    }
}
