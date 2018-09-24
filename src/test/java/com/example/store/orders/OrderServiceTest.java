package com.example.store.orders;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    public void should_create_order() {
        Order order = new Order();
        order.setId("order-id");
        given(orderRepository.save(order)).willReturn(order);
        Order saved = orderService.save(order);
        Assert.assertNotNull(saved.getId());
    }
}
