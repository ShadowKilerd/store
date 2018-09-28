package com.example.store.shoppingitems;

import com.example.store.products.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShoppingItemService {

    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    @Autowired
    private ProductService productService;


    public ShoppingItem save(ShoppingItem item) {
        return shoppingItemRepository.save(item);
    }

    public ShoppingItem findById(String id){
        Optional<ShoppingItem> fetched = this.shoppingItemRepository.findById(id);
        ShoppingItem shoppingItem = fetched.get();

        shoppingItem.setProduct(productService.getProduct(shoppingItem.getProductId()));
        return shoppingItem;
    }
}
