package com.price.dto;


import java.util.Date;

public class FollowProductDTO {
    private long productId;
    private String productName;
    private Date followDate;
    private float priceNow;
    private float priceTrend;

    public FollowProductDTO() {}

    public FollowProductDTO(long productId, String productName, Date followDate, float priceNow, float priceTrend) {
        this.productId = productId;
        this.productName = productName;
        this.followDate = followDate;
        this.priceNow = priceNow;
        this.priceTrend = priceTrend;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getFollowDate() {
        return followDate;
    }

    public void setFollowDate(Date followDate) {
        this.followDate = followDate;
    }

    public float getPriceNow() {
        return priceNow;
    }

    public void setPriceNow(float priceNow) {
        this.priceNow = priceNow;
    }

    public float getPriceTrend() {
        return priceTrend;
    }

    public void setPriceTrend(float priceTrend) {
        this.priceTrend = priceTrend;
    }
}
