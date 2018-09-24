package com.example.store.products;


import com.example.store.exception.OutOfAmountException;
import com.example.store.orders.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void checkOutOfAmount(List<OrderItem> orderItems) {
        orderItems.forEach(item -> {
            Integer productId = item.getOrderProduct().getProductId();
            Optional<Product> product = productRepository.findById(productId);
            if (!product.isPresent() || product.get().isOutOfAmount(item.getAmount())) {
                throw new OutOfAmountException();
            }
        });
    }
}
