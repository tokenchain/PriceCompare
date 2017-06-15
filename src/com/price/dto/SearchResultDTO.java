package com.price.dto;

import com.price.model.Product;

import java.util.List;

public class SearchResultDTO {
    private List<Product> products;
    private int keywordCount;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getKeywordCount() {
        return keywordCount;
    }

    public void setKeywordCount(int keywordCount) {
        this.keywordCount = keywordCount;
    }
}
