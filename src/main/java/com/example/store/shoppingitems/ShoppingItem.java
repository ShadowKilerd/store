package com.example.store.shoppingitems;

import com.example.store.products.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ShoppingItem {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    @PrimaryKeyJoinColumn
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

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
