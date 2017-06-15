package com.price.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "product")
public class Product {
    private long id;
    private String name;
    private String img_url;
    private String shop_name;
    private int category_id;

    public Product() {}

    public Product(long id, String name, String img_url, String shop_name, int category_id) {
        this.id = id;
        this.name = name;
        this.img_url = img_url;
        this.shop_name = shop_name;
        this.category_id = category_id;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    @Override
    public String toString() {
        return id+"|"+name+"|"+img_url+"|"+shop_name+"|"+category_id;
    }
}
