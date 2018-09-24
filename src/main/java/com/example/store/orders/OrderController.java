package com.example.store.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/api/users/{userId}/orders")
    public ResponseEntity createOrder(@RequestBody Order order,
                                      @PathVariable String userId,
                                      UriComponentsBuilder builder) {
        String orderId = UUID.randomUUID().toString();
        String orderItemSql = "insert into order_item(id, order_id,amount,product_name,product_price,product_unit,product_img_url) values('%s','%s',%d,'%s',%s,'%s','%s')";
        String orderSql = "insert into orders(id, user_id) values('%s','%s')";
        order.getOrderItems().forEach(orderItem -> {
            jdbcTemplate.execute(String.format(orderItemSql, UUID.randomUUID().toString(), orderId, orderItem.getAmount()
                    , orderItem.getName(), orderItem.getPrice().toString(), orderItem.getUnit(), orderItem.getImgUrl())
            );
        });
        jdbcTemplate.execute(String.format(orderSql, orderId, userId));
        return ResponseEntity.created(builder.path("/api/users/" + userId + "/orders/" + orderId).build().toUri()).build();
    }
}
