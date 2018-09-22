package com.example.store.shoppingitems;

import com.example.store.products.Product;
import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShoppingItemsIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private Flyway flyway;

    @Before
    public void setUp() throws Exception {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void should_create_shopping_item_by_user_id() {
        ShoppingItem item = new ShoppingItem();
        item.setProduct(new Product());
        item.getProduct().setId(1);
        item.setAmount(10);

        URI uri = restTemplate.postForLocation("/api/users/user-id-1／shopping-items", item);
        assertTrue(uri.getPath().startsWith("/api/users/user-id-1/shopping-items/"));

        String id = uri.getPath().substring("/api/users/user-id-1/shopping-items/".length());
        Map<String, Object> itemSaved = jdbcTemplate.queryForMap("select * from shopping_items where user_id ='user-id-1' and id='" + id + "'");
        assertThat(Integer.parseInt(itemSaved.get("product_id").toString()), is(item.getProduct().getId()));
        assertThat(Integer.parseInt(itemSaved.get("amount").toString()), is(item.getAmount()));
    }
}
