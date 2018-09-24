package com.example.store.orders;

import com.example.store.base.IntegrationTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class OrderIntegrationTest extends IntegrationTestBase {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void should_create_order() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        orderItem.setName("可乐");
        orderItem.setPrice(BigDecimal.valueOf(4.50));
        orderItem.setUnit("瓶");
        orderItem.setImgUrl("/api/imgs/1");
        orderItem.setAmount(10);
        order.setOrderItems(Collections.singletonList(orderItem));

        URI uri = restTemplate.postForLocation("/api/users/user-id-1/orders", order);
        assertTrue(uri.getPath().startsWith("/api/users/user-id-1/orders/"));

        String id = uri.getPath().substring("/api/users/user-id-1/orders/".length());
        Map<String, Object> orderSaved = jdbcTemplate.queryForMap("select * from orders where user_id ='user-id-1' and id='" + id + "'");
        assertNotNull(orderSaved);
        Map<String, Object> itemSaved = jdbcTemplate.queryForMap("select * from order_item where order_id ='" + id + "'");
        assertThat(itemSaved.size(), is(7));
    }
}
