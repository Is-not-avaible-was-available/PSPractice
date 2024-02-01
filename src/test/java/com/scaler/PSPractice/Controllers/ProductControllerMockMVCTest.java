package com.scaler.PSPractice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Services.FakeStoreProductService;
import com.scaler.PSPractice.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerMockMVCTest {
    @Autowired
    private ProductController productController;
    @MockBean
    private FakeStoreProductService fakeStoreProductService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProductByIdAPI() throws Exception {
        GenericProductDTO productDTO = new GenericProductDTO();
        productDTO.setId("2bad52dd-9063-48a6-bc9d-0d73dab840fe");
        productDTO.setTitle("iPhone");
        productDTO.setCategory("Electronics");

        when(fakeStoreProductService.getProductById("2bad52dd-9063-48a6-bc9d-0d73dab840fe")).thenReturn(productDTO);

       ResultActions resultActions = mockMvc.perform(get("/products/2bad52dd-9063-48a6-bc9d-0d73dab840fe")).
                andExpect(status().is(200))
               .andExpect(content().json("{\"id\":\"2bad52dd-9063-48a6-bc9d-0d73dab840fe\",\"title\":\"iPhone\",\"description\":null,\"image\":null,\"price\":0.0,\"category\":\"Electronics\"}"))
               .andExpect(jsonPath("$.id").value("2bad52dd-9063-48a6-bc9d-0d73dab840fe"));
       String responseString =
       resultActions.andReturn().getResponse().getContentAsString();
       Assertions.assertEquals("{\"id\":\"2bad52dd-9063-48a6-bc9d-0d73dab840fe\"" +
               ",\"title\":\"iPhone\",\"description\":null" +
               ",\"image\":null,\"price\":0.0,\"category\":\"Electronics\"}",responseString);

       GenericProductDTO genericProductDTO = objectMapper.readValue(responseString, GenericProductDTO.class);
       Assertions.assertNotNull(genericProductDTO);
       Assertions.assertEquals(genericProductDTO.getId(), productDTO.getId());
        System.out.println(responseString);
    }
}
