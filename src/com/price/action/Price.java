package com.price.action;

import com.opensymphony.xwork2.ActionSupport;
import com.price.dto.ProductPriceDTO;
import com.price.model.ProductPrice;
import com.price.service.PriceService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component("price")
@Scope("prototype")
public class Price extends ActionSupport {
    @Resource
    private PriceService priceService;
    private String skuIds;
    private long getHistoryPriceId;
    private List<ProductPriceDTO> prices;
    private List<ProductPrice> historyPrices;

    @Override
    public String execute() throws Exception {
        prices = priceService.getLastPriceByIds(skuIds);
        return SUCCESS;
    }

    public String getLowestPrice() throws Exception {
        prices = priceService.getLowestPriceByIds(skuIds);
        return SUCCESS;
    }

    public String getHistoryPrice() throws Exception {
        historyPrices = priceService.getHistoryPriceById(getHistoryPriceId);
        return SUCCESS;
    }

    public void setSkuIds(String skuIds) {
        this.skuIds = skuIds;
    }

    public List getPrices() {
        return prices;
    }

    public void setGetHistoryPriceId(long getHistoryPriceId) {
        this.getHistoryPriceId = getHistoryPriceId;
    }

    public List<ProductPrice> getHistoryPrices() {
        return historyPrices;
    }
}
