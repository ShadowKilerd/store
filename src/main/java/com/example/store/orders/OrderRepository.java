package com.example.store.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class OrderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Order save(Order order) {
        String orderId = UUID.randomUUID().toString();
        order.setId(orderId);
        String orderItemSql = "insert into order_item(id, order_id,amount,product_name,product_price,product_unit,product_img_url) values('%s','%s',%d,'%s',%s,'%s','%s')";
        String orderSql = "insert into orders(id, user_id) values('%s','%s')";
        order.getOrderItems().forEach(orderItem -> {
            jdbcTemplate.execute(String.format(orderItemSql, UUID.randomUUID().toString(), orderId, orderItem.getAmount()
                    , orderItem.getName(), orderItem.getPrice().toString(), orderItem.getUnit(), orderItem.getImgUrl())
            );
        });
        jdbcTemplate.execute(String.format(orderSql, orderId, order.getUserId()));
        return order;
    }
}
