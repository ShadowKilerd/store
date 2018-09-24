package com.example.store.products;

import com.example.store.exception.OutOfAmountException;
import com.example.store.orders.OrderItem;
import com.example.store.orders.OrderProduct;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void should_get_products_list_when_product_list() {
        given(productRepository.findAll())
                .willReturn(Lists.newArrayList(new Product(), new Product()));

        List<Product> products = productService.getProducts();
        Assert.assertThat(products.size(), is(2));
    }

    @Test(expected = OutOfAmountException.class)
    public void should_throw_exception_when_order_amount_is_bigger_than_product_total_amount() {
        Product product1 = new Product();
        product1.setTotalAmount(10);
        given(productRepository.findById(1)).willReturn(Optional.of(product1));
        OrderItem orderItem = new OrderItem();
        OrderProduct product = new OrderProduct();
        product.setProductId(1);
        orderItem.setOrderProduct(product);
        orderItem.setAmount(1000);
        productService.checkOutOfAmount(Collections.singletonList(orderItem));
    }
}
