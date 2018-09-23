package com.example.store.shoppingitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ShoppingItemService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ShoppingItem save(ShoppingItem item) {
        String sql = "insert into shopping_items(id, user_id, product_id, amount) values('%s','%s','%d','%d')";
        String id = UUID.randomUUID().toString();
        jdbcTemplate.execute(String.format(sql, id, "user-id-1", item.getProduct().getId(), item.getAmount()));
        item.setId(id);
        return item;
    }
}
