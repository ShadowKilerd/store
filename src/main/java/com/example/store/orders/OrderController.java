package com.example.store.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity createOrder(@RequestBody Order order,
                                      @PathVariable String userId,
                                      UriComponentsBuilder builder) {
        order.setUserId(userId);
        Order orderSaved = orderService.save(order);
        return ResponseEntity.created(builder.path("/api/users/" + userId + "/orders/" + orderSaved.getId()).build().toUri()).build();
    }
}
