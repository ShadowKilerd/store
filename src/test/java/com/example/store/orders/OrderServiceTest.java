package com.example.store.orders;

import com.example.store.exception.OutOfAmountException;
import com.example.store.products.ProductService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @Test
    public void should_create_order() {
        Order order = new Order();
        order.setId("order-id");
        given(orderRepository.save(order)).willReturn(order);
        Order saved = orderService.save(order);
        Assert.assertNotNull(saved.getId());
    }

    @Test(expected = OutOfAmountException.class)
    public void should_not_create_order_when_amount_is_bigger_than_product_total_amount() {
        doThrow(OutOfAmountException.class).when(productService).checkOutOfAmount(anyList());
        Order order = new Order();
        order.setId("order-id");

        OrderItem orderItem = new OrderItem();
        order.setOrderItems(Collections.singletonList(orderItem));
        orderService.save(order);
    }
}
