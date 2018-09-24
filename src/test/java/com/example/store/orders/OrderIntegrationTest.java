package com.example.store.orders;

import com.example.store.base.IntegrationTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class OrderIntegrationTest extends IntegrationTestBase {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @Transactional
    public void should_create_order() {
        Order order = new Order();
        OrderItem orderItem = new OrderItem();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setName("可乐");
        orderProduct.setPrice(BigDecimal.valueOf(4.50));
        orderProduct.setUnit("瓶");
        orderProduct.setImgUrl("/api/imgs/1");
        orderItem.setOrderProduct(orderProduct);
        orderItem.setAmount(10);

        OrderItem orderItem1 = new OrderItem();
        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setName("雪碧");
        orderProduct1.setPrice(BigDecimal.valueOf(3.50));
        orderProduct1.setUnit("瓶");
        orderProduct1.setImgUrl("/api/imgs/2");
        orderItem1.setOrderProduct(orderProduct1);
        orderItem1.setAmount(6);
        order.setOrderItems(Arrays.asList(orderItem, orderItem1));

        URI uri = restTemplate.postForLocation("/api/users/user-id-1/orders", order);
        assertTrue(uri.getPath().startsWith("/api/users/user-id-1/orders/"));

        String id = uri.getPath().substring("/api/users/user-id-1/orders/".length());
        Optional<Order> orderSaved = orderRepository.findById(id);
        assertTrue(orderSaved.isPresent());
        assertThat(orderSaved.get().getOrderItems().size(), is(2));
    }
}
