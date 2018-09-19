package com.example.store.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @GetMapping("/api/products")
    public List<Product> getProductsList() {
        ArrayList<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(4.5));
        product.setUnit("瓶");
        product.setTotalAmount(BigDecimal.valueOf(10));
        product.setImgUrl("/api/img/1");
        products.add(product);

        products.add(new Product());

        return products;
    }
}
