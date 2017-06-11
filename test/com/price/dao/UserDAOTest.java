package com.price.dao;

import com.price.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.sql.SQLException;

@ContextConfiguration("/beans.xml")
public class UserDAOTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void saveTest() throws SQLException {
        User u = new User("name", "pwd", "email", (byte)1, "active");
        userDAO.save(u);
    }


}
