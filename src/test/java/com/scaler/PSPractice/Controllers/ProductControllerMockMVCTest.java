package com.scaler.PSPractice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
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
    @Qualifier("fakeStoreProductService")
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void testGetProductByIdAPI() throws Exception {
//        GenericProductDTO productDTO = new GenericProductDTO();
//        productDTO.setId("bb6996d0-dfe7-4526-84ff-604717872e94");
//        productDTO.setTitle("iPhone");
//        productDTO.setCategory("Electronics");
//
//        when(productService.getProductById("bb6996d0-dfe7-4526-84ff-604717872e94")).thenReturn(productDTO);
//
//       ResultActions resultActions = mockMvc.perform(get("/products/bb6996d0-dfe7-4526-84ff-604717872e94")).
//                andExpect(status().is(200));
//       String responseString =
//       resultActions.andReturn().getResponse().getContentAsString();
//        System.out.println(responseString);
//    }
}
