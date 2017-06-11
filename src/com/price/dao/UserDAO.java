package com.price.dao;

import com.price.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

}
