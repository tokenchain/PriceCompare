package com.price.dto;


public class LowestPriceDTO {
    private long id;
    private float price;

    public LowestPriceDTO() {}

    public LowestPriceDTO(long id, float price) {
        this.id = id;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
