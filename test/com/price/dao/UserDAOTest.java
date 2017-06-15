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
public class UserDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
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

    @Test
    public void modiyUsernameTest() throws Exception {
        userDAO.modifyUsername(32, "31");
        User user = userDAO.hasUser("2414170303@qq.com", "123");
        System.out.println(user.getUsername());
    }

    @Test
    public void usernameChangedTest() throws Exception {
        System.out.println(userDAO.usernameChanged(32));
    }

    @Test
    public void modifyPasswordTest() throws Exception {
        userDAO.modifyPassword(32, "321");
        User user1 = userDAO.hasUser("2414170303@qq.com", "123");
        User user2 = userDAO.hasUser("2414170303@qq.com", "321");
        System.out.println(user1 == null);
        System.out.println(user2.getUsername() + "|" + user2.getPassword());
    }

}
