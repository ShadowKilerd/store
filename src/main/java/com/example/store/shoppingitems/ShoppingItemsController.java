package com.example.store.shoppingitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ShoppingItemsController {
    @Autowired
    private ShoppingItemService shoppingItemService;

    @PostMapping("/api/users/user-id-1/shopping-items")
    public ResponseEntity createShoppingItems(@RequestBody ShoppingItem item, UriComponentsBuilder builder) {
        String id = shoppingItemService.save(item).getId();
        return ResponseEntity.created(builder.path("/api/users/user-id-1/shopping-items/" + id).build().toUri()).build();
    }
}
