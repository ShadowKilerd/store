package com.example.store.products;

import com.example.store.base.IntegrationTestBase;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ProductsIntegrationTest extends IntegrationTestBase {

    @Test
    public void should_return_product_lists_when_call_get_products_list() {
        ResponseEntity<List<Product>> productsEntity = restTemplate.exchange
                ("/api/products", HttpMethod.GET,
                        null, new ParameterizedTypeReference<List<Product>>() {
                        });
        assertThat(productsEntity.getStatusCode(), is(HttpStatus.OK));
        List<Product> products = productsEntity.getBody();
        assertEquals(2, products.size());
        Product product = products.get(0);
        assertThat(product.getName(), is("可乐"));
        assertThat(product.getPrice().doubleValue(), is(4.50));
        assertThat(product.getUnit(), is("瓶"));
        assertThat(product.getTotalAmount(), is(1000));
        assertThat(product.getImgUrl(), is("/api/imgs/1"));
    }
}
