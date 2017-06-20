package com.price.dao;

import com.price.model.Follow;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@ContextConfiguration("/beans.xml")
public class FollowDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private FollowDAO followDAO;

    @Test
    public void saveTest() throws SQLException {
        followDAO.save(32, 104256,  new Date(), 12.3f);
        List list = followDAO.getProductIdsByUserId(32);
        System.out.println(list);
    }

    @Test
    public void getProductIdsByUserId() throws SQLException {
        List list = followDAO.getProductIdsByUserId(32);
        System.out.println(list);
    }

    @Test
    public void deleteTest() throws Exception {
        followDAO.delete(32, 104256);
        List list = followDAO.getProductIdsByUserId(32);
        System.out.println(list);
    }

    @Test
    public void hasFollowTest() throws Exception {
        System.out.println(followDAO.hasFollow(33,104256));
    }

    @Test
    public void getFollowDetailByUserId() throws Exception {
        List<Follow> l = followDAO.getFollowDetailByUserId(32);
        for(Follow f : l) {
            System.out.println(f.getUserId() + "|" + f.getProductId() + "|" + f.getDate() + "|" + f.getPrice());
        }
    }
}
