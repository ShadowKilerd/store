package com.example.store.products;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

public class Product {

    private Integer id;
    private BigDecimal price;
    private String unit;
    private Integer totalAmount;
    private String imgUrl;
    private String name;


    public Product() {
    }

    public boolean isOutOfAmount(Integer amount) {
        return totalAmount.compareTo(amount) < 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
