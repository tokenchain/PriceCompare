package com.price.service;

import com.price.dao.PriceDAO;
import com.price.dto.LowestPriceDTO;
import com.price.dto.ProductPriceDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Component("priceService")
public class PriceService {
    @Resource
    private PriceDAO priceDAO;

    public List<ProductPriceDTO> getLastPriceByIds(String ids) {
        if (!ids.matches("\\d+[,\\d+]*")) {
            return null;
        }
        if(ids.length() > 300) {
            return null;
        }
        try {
            return priceDAO.getLastPriceByIds(ids);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<ProductPriceDTO> getLowestPriceByIds(String ids) {
        if (!ids.matches("\\d+[,\\d+]*")) {
            return null;
        }
        if(ids.length() > 300) {
            return null;
        }
        try {
            return priceDAO.getLowestPriceByIds(ids);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


}
