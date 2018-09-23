package com.example.store.shoppingitems;

import com.example.store.products.Product;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class ShoppingItemRepositoryTest {
    @Autowired
    private ShoppingItemRepository shoppingItemRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void should_save_shopping_item_to_database() {
        ShoppingItem item = new ShoppingItem();

        Product product = new Product();
        product.setId(1);
        item.setProduct(product);
        item.setAmount(1);

        ShoppingItem saved = shoppingItemRepository.save(item);
        Assert.assertThat(saved.getId().length(), is(36));
    }

}
