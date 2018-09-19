package com.example.store.products;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

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
}
