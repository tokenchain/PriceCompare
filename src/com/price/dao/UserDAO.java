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

    public User hasUser(String email, String password) throws SQLException {
        User u = null;
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select new com.price.model.User(u.id, u.username, u.password, u.email, u.state, u.active_code, u.username_changed) from user u where u.email = :email and u.password = :password") ;
        q.setParameter("email", email);
        q.setParameter("password", password);
        u = (User)q.uniqueResult();
        return u;
    }

    public void modifyUsername(int id, String username) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("update user u set u.username = :username, u.username_changed = true where u.id = :id");
        q.setParameter("username", username);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public void modifyPassword(int id, String password) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("update user u set u.password = :password where u.id = :id");
        q.setParameter("password", password);
        q.setParameter("id", id);
        q.executeUpdate();
    }

    public boolean usernameChanged(int id) throws SQLException {
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery("select u.username_changed from user u where u.id = :id");
        q.setParameter("id", id);
        Boolean b = (Boolean)q.uniqueResult();
        return b;
    }

}
