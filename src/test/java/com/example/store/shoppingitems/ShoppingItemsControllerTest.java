package com.example.store.shoppingitems;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@WebMvcTest(ShoppingItemsController.class)
public class ShoppingItemsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ShoppingItemService shoppingItemService;

    @Test
    public void should_create_shopping_item() throws Exception {
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setId("shopping-item-id");
        given(shoppingItemService.save(any(ShoppingItem.class))).willReturn(shoppingItem);

        mockMvc.perform(
                post("/api/users/user-id-1/shopping-items")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(shoppingItem)))
                .andExpect(header().stringValues("Location", "http://localhost/api/users/user-id-1/shopping-items/shopping-item-id"));
    }

}
