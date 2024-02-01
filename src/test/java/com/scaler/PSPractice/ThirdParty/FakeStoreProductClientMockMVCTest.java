package com.scaler.PSPractice.ThirdParty;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FakeStoreProductClientMockMVCTest {

    @Autowired
    private FakeStoreProductClient fakeStoreProductClient;
    @MockBean
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    public void testGetProductByIdAPI() throws Exception {

        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("637a6546-2d86-4a55-b16e-200d257e2973");
        fakeStoreProductDTO.setTitle("Iphone 15");
        fakeStoreProductDTO.setCategory("Electronics");

        ResponseEntity<FakeStoreProductDTO> responseEntityMock = new ResponseEntity<>(fakeStoreProductDTO
                , HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(String.class)))
                .thenReturn(responseEntityMock);

        ResultActions resultActions =
        mockMvc.perform(get("/products/2bad52dd-9063-48a6-bc9d-0d73dab840fe"))
                .andExpect(status().is(200))
                .andExpect(content().json("{\"id\":\"637a6546-2d86-4a55-b16e-200d257e2973\",\"title\":\"Iphone 15\",\"description\":null" +
                        ",\"image\":null,\"price\":0.0,\"category\":\"Electronics\"}"));

        String responseString = resultActions.andReturn().getResponse().getContentAsString();
        FakeStoreProductDTO response = objectMapper.readValue(responseString, FakeStoreProductDTO.class);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(fakeStoreProductDTO.getId(), response.getId());
    }
}
