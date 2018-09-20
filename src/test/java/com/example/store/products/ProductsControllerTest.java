package com.example.store.products;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void should_get_product_list_when_get_product_list() throws Exception {
        given(productService.getProducts()).willReturn(getTestProducts());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("可乐"))
                .andExpect(jsonPath("$[1].name").value("雪碧"));
    }

    private List<Product> getTestProducts() {
        ArrayList<Product> products = new ArrayList<>();

        Product product = new Product();
        product.setName("可乐");
        product.setPrice(BigDecimal.valueOf(4.5));
        product.setUnit("瓶");
        product.setTotalAmount(10);
        product.setImgUrl("/api/img/1");
        products.add(product);

        Product product1 = new Product();
        product1.setName("雪碧");
        products.add(product1);

        return products;
    }

}
