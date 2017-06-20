package com.price.dao;

import com.price.model.Follow;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component("followDAO")
public class FollowDAO {
    @Resource
    private SessionFactory sessionFactory;

    public void save(int userId, long productId, Date date, float price) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.save(new Follow(userId, productId, date, price));
    }

    public void delete(int userId, long prductId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("delete from follow f where f.userId = :userId and f.productId = :productId");
        q.setParameter("userId", userId);
        q.setParameter("productId", prductId);
        q.executeUpdate();
    }

    public boolean hasFollow(int userId, long prductId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select id from follow f where f.userId = :userId and f.productId = :productId");
        q.setParameter("userId", userId);
        q.setParameter("productId", prductId);
        Integer id = (Integer)q.uniqueResult();
        if(id != null && id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Long> getProductIdsByUserId(int userId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select f.productId from follow f where f.userId = :userId");
        q.setParameter("userId", userId);
        List<Long> list = q.list();
        return list;
    }

    public List<Follow> getFollowDetailByUserId(int userId) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select new com.price.model.Follow(f.userId, f.productId, f.date, f.price) from follow f where f.userId = :userId");
        q.setParameter("userId", userId);
        List<Follow> follows = q.list();
        return follows;
    }
}
