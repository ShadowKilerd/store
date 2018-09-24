package com.example.store.orders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class OrderRepositoryTest {

    @Autowired
    private
    OrderRepository orderRepository;

    @Test
    public void should_save_order() {
        OrderProduct product = new OrderProduct();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(4.50));
        product.setUnit("瓶");
        product.setImgUrl("/api/imgs/1");

        OrderItem orderItem = new OrderItem();
        orderItem.setAmount(10);
        orderItem.setOrderProduct(product);

        Order order = new Order();
        order.setOrderItems(Collections.singletonList(orderItem));
        Order saved = orderRepository.save(order);

        Assert.assertThat(saved.getId().length(), is(36));
    }
}
