package com.price.dao;

import com.price.model.Product;
import com.price.util.Lucene;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component("searchDAO")
public class SearchDAO {
    @Resource
    private SessionFactory sessionFactory;
    private Lucene lucene = Lucene.getLucene();


    /*
    * 查找Id对应Product
    * @param id
    * */
    public Product searchById(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Product result = session.get(Product.class, id);
        return result;
    }


    /*
    * 查找关键词
    * @param keywword
    * @param page
    * @param size
    * */
    public List<Product> search(String keyword, int page, int size) throws Exception {
        if(page < 0 || size < 0 || keyword.trim().equals("")) {
            return new ArrayList<>();
        }
        List<Long> list = lucene.search(keyword);
        if(list.size() == 0) {
            return new ArrayList<>();
        }
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select new com.price.model.Product(p.id, p.name, p.img_url, p.shop_name, p.category_id) from product p where p.id in (:list)") ;
        if(page * size <= list.size()) {
            q.setParameterList("list",list.subList((page - 1) * size, page * size));
        } else {
            //获取页面超出最大页面时，返回最后一页信息
            int lastPage = (int)Math.ceil(list.size()*1.0/size) - 1;
            q.setParameterList("list",list.subList(lastPage * size, list.size()));
        }
        List<Product> result = q.list();
        return result;
    }

    /*
    * 统计关键词结果数量
    * @param keyword
    * */
    public int countKeyword(String keyword) throws SQLException {
        if(keyword == null) {
            return 0;
        }
        try {
            int count = lucene.countKeyword(keyword);
            return count;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
