package com.example.store.products;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_query_all_products_from_database() {

        Product product = new Product();
        product.setId(1);
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(4.5));
        product.setUnit("瓶");
        product.setTotalAmount(10);
        product.setImgUrl("/api/img/1");
        entityManager.persist(product);

        Product product1 = new Product();
        product1.setId(2);
        product1.setName("雪碧");
        product1.setPrice(BigDecimal.valueOf(4.5));
        product1.setUnit("瓶");
        product1.setTotalAmount(10);
        product1.setImgUrl("/api/img/2");
        entityManager.persistAndFlush(product1);

        List<Product> products = productRepository.findAll();

        Assert.assertThat(products.size(), is(2));
    }


}