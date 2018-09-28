package com.example.store.shoppingitems;

import com.example.store.base.IntegrationTestBase;
import com.example.store.products.Product;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.URI;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class ShoppingItemsIntegrationTest extends IntegrationTestBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void should_create_shopping_item_by_user_id() {
        ShoppingItem item = new ShoppingItem();
        Product product = new Product();
        product.setId(1);
        item.setProduct(product);
        item.setProductId(1);
        item.setAmount(10);

        URI uri = restTemplate.postForLocation("/api/users/user-id-1/shopping-items", item);
        assertTrue(uri.getPath().startsWith("/api/users/user-id-1/shopping-items/"));

        String id = uri.getPath().substring("/api/users/user-id-1/shopping-items/".length());
        Map<String, Object> itemSaved = jdbcTemplate.queryForMap("select * from shopping_item where user_id ='user-id-1' and id='" + id + "'");
        assertThat(Integer.parseInt(itemSaved.get("product_id").toString()), is(item.getProduct().getId()));
        assertThat(Integer.parseInt(itemSaved.get("amount").toString()), is(item.getAmount()));
    }
}
