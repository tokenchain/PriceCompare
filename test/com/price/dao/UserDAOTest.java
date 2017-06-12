package com.price.dao;

import com.price.model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.UUID;

@ContextConfiguration("/beans.xml")
public class UserDAOTest  extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    UserDAO userDAO;

    @Test
    public void saveTest() throws SQLException {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        User u = new User("name", "pwd", "email", (byte)1, uuid.toString());
        userDAO.save(u);
    }

    @Test
    public void hasUserTest() throws Exception {
        User u = userDAO.hasUser("2414170303@qq.com", "123");
        System.out.println(u.getActive_code() + "|" + u.getState());
    }

}
