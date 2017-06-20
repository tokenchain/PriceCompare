package com.price.service;

import com.price.dao.FollowDAO;
import com.price.model.Follow;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Component("followService")
public class FollowService {
    @Resource
    private FollowDAO followDAO;

    public boolean save(int userId, long productId, float price) {
        try {
            followDAO.save(userId, productId, new Date(), price);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int userId, long productId) {
        try {
            followDAO.delete(userId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean hasFollow(int userId, long productId) {
        try {
            return followDAO.hasFollow(userId, productId);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Long> getProductIdsByUserId(int id) {
        try {
            return followDAO.getProductIdsByUserId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Follow> getFollowDetailByUserId(int userId){
        try {
            return followDAO.getFollowDetailByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
