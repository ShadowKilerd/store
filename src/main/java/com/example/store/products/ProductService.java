package com.example.store.products;

import com.example.store.exception.OutOfAmountException;
import com.example.store.orders.OrderItem;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class ProductService {
    private RestTemplate restTemplate;

    public ProductService() {
        restTemplate = new RestTemplate();
    }

    public Product getProduct(Integer id) {
        if(id == null) {
            return new Product();
        }

        HttpHeaders headers = new HttpHeaders();

        headers.set("X-user-detail","{\"userId\": \"service\", \"roles\":[\"ROLE_SERVICE\"]}");

        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<Product> exchange = restTemplate.exchange("http://localhost:9091/" + id, HttpMethod.GET, httpEntity, Product.class);
        return exchange.getBody();
    }

    public void checkOutOfAmount(List<OrderItem> orderItems) {
        orderItems.forEach(item -> {
            Integer productId = item.getOrderProduct().getProductId();
            Product product = this.getProduct(productId);
            if (product.isOutOfAmount(item.getAmount())) {
                throw new OutOfAmountException();
            }
        });
    }
}
