package com.example.store.orders;

import com.example.store.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    public Order save(Order order) {
        productService.checkOutOfAmount(order.getOrderItems());
        return orderRepository.save(order);
    }
}
