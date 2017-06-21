package com.price.dao;

import com.price.dto.LowestPriceDTO;
import com.price.dto.ProductPriceDTO;
import com.price.model.ProductPrice;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component("priceDAO")
public class PriceDAO {
    @Resource
    private SessionFactory sessionFactory;

    public List<ProductPriceDTO> getLastPriceByIds(String ids) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery("SELECT product_id, price, save_date FROM" +
                "  (SELECT max(id) id_max from product_price WHERE product_id IN (" + ids +
                ") GROUP BY product_id) as t LEFT JOIN product_price p" +
                "    ON p.id = t.id_max;");
        List prices = q.list();
        List<ProductPriceDTO> list = new ArrayList<>(prices.size());
        ProductPriceDTO productPriceDTO;
        Object[] objects;
        for (Object o : prices) {
            productPriceDTO = new ProductPriceDTO();
            objects = (Object[])o;
            productPriceDTO.setProductId(((BigInteger)objects[0]).longValue());
            productPriceDTO.setPrice((float)objects[1]);
            productPriceDTO.setDate((Date) objects[2]);
            list.add(productPriceDTO);
        }
        return list;
    }

    public List<ProductPriceDTO> getLowestPriceByIds(String ids) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createNativeQuery("SELECT product_id,MIN(price) FROM product_price WHERE product_id IN (" +
                ids + ") GROUP BY product_id");
        List prices = q.list();
        List<ProductPriceDTO> list = new ArrayList<>();
        ProductPriceDTO productPriceDTO = null;
        Object[] objects;
        for (Object o : prices) {
            productPriceDTO = new ProductPriceDTO();
            objects = (Object[])o;
            productPriceDTO.setProductId(((BigInteger)objects[0]).longValue());
            productPriceDTO.setPrice((float)objects[1]);
            list.add(productPriceDTO);
        }
        return list;
    }

    public List<ProductPrice> getHistoryPriceById(long id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("from productPrice where productId = :id order by date ");
        q.setParameter("id", id);
        q.setMaxResults(7);
        List<ProductPrice> list = q.list();
        return list;
    }
}
