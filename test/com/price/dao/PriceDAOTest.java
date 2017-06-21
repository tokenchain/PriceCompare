package com.price.dao;

import com.price.action.Price;
import com.price.dto.LowestPriceDTO;
import com.price.dto.ProductPriceDTO;
import com.price.model.ProductPrice;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ContextConfiguration("/beans.xml")
public class PriceDAOTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Resource
    private PriceDAO priceDAO;

    @Test
    public void getPriceByIdsTest() throws Exception {
        List<ProductPriceDTO> prices = priceDAO.getLastPriceByIds("120604,166133,185933,136328,166139,240215,273614");
        for (ProductPriceDTO p : prices) {
            System.out.println(p.getProductId() + "|" + p.getPrice() + "|" + p.getDate());
        }
    }

    @Test
    public void getLowestPriceTest() throws Exception {
        List<ProductPriceDTO> prices = priceDAO.getLowestPriceByIds("120604,166133,185933,136328,166139,240215,273614");
        for(ProductPriceDTO l : prices) {
            System.out.println(l.getProductId() + "|" + l.getPrice());
        }
    }

    @Test
    public void getHistoryPriceById() throws Exception {
        List<ProductPrice> list = priceDAO.getHistoryPriceById(118717);
        for(ProductPrice p : list) {
            System.out.println(p.getProductId() + "|" + p.getPrice() + "|" + p.getDate());
        }
    }

}
