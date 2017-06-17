package com.price.dao;

import com.price.action.Price;
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
        List prices = priceDAO.getLastPriceByIds("120604,166133,185933,136328,166139,240215,273614");
        for (Object o : prices) {
            Object[] objs = (Object[])o;
            System.out.println(objs[0] + "|" + objs[1] + "|" + objs[2]);
        }
    }
}
