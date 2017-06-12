package com.price.dao;

import com.price.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;

@Component("userDAO")
public class UserDAO {
    @Resource
    private SessionFactory sessionFactory;

    public void save(User user) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public boolean isEmailUsed(String email) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select count(*) from user u where u.email = :email") ;
        q.setParameter("email",email);
        if((Long)q.uniqueResult() > 0) {
            return true;
        }
        return false;
    }

    public void active(String code) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("update user u set u.state = :state where u.active_code = :code") ;
        q.setParameter("state",(byte)1);
        q.setParameter("code", code);
        q.executeUpdate();
    }

}
