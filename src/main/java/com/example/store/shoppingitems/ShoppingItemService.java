package com.example.store.shoppingitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingItemService {

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;


    public ShoppingItem save(ShoppingItem item) {
        return shoppingItemRepository.save(item);
    }
}
