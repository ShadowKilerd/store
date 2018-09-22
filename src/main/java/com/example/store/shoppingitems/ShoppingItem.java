package com.example.store.shoppingitems;

import com.example.store.products.Product;

public class ShoppingItem {
    private Product product;
    private Integer amount;

    public Product getProduct() {
        return this.product;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
