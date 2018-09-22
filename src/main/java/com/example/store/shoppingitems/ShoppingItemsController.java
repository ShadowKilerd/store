package com.example.store.shoppingitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
public class ShoppingItemsController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostMapping("/api/users/user-id-1／shopping-items")
    public ResponseEntity createShoppingItems(@RequestBody ShoppingItem item, UriComponentsBuilder builder) {
        String sql = "insert into shopping_items(id, user_id, product_id, amount) values('%s','%s','%d','%d')";
        String id = UUID.randomUUID().toString();
        jdbcTemplate.execute(String.format(sql, id, "user-id-1", item.getProduct().getId(), item.getAmount()));
        return ResponseEntity.created(builder.path("/api/users/user-id-1/shopping-items/" + id).build().toUri()).build();
    }
}
