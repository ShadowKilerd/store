package com.example.store.shoppingitems;

import com.example.store.products.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ShoppingItemsServiceTest {
    @InjectMocks
    private ShoppingItemService shoppingItemService;

    @Mock
    private ShoppingItemRepository shoppingItemRepository;

    @Test
    public void should_create_shopping_item() {
        ShoppingItem item = new ShoppingItem();
        item.setId("shopping-item-id");
        item.setAmount(10);
        Product product = new Product();
        product.setId(1);
        item.setProduct(product);
        given(shoppingItemRepository.save(item)).willReturn(item);

        ShoppingItem saved = shoppingItemService.save(item);
        Assert.assertNotNull(saved.getId());
    }

}
