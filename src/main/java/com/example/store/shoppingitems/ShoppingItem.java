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
    
    @Transient
    private Product product;

    private Integer productId;

    private Integer amount;
    private String userId;


    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }


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
        if (product != null) {
            this.setProductId(product.getId());
        }
//        this.setProductId(product.getId());
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
