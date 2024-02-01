package com.scaler.PSPractice.ThirdParty;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
public class FakeStoreProductClientTest {
    @Autowired
    private FakeStoreProductClient fakeStoreProductClient;

    @MockBean
    private RestTemplateBuilder restTemplateBuilderMock;



//    @Test
//    public void testGetProductByIdAPI() throws NotFoundException {
//        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
//        when(restTemplateBuilderMock.build()).thenReturn(restTemplate);
//        ResponseEntity<FakeStoreProductDTO> responseMock = new ResponseEntity<>(null, HttpStatus.OK);
//        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(String.class)))
//                .thenReturn(responseMock);
//
//        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.getProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
//        Assertions.assertNull(fakeStoreProductDTO);
//    }

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        fakeStoreProductDTO.setTitle("Real Mix Fruit Juice");
        fakeStoreProductDTO.setCategory("Food");
        fakeStoreProductDTO.setImage("www.real.com");
        fakeStoreProductDTO.setPrice(120.0);
        fakeStoreProductDTO.setDescription("Fresh Fruit Juice");

        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplate);

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);

        when(restTemplate.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(String.class)))
                .thenReturn(fakeStoreProductDTOResponseEntity);
        FakeStoreProductDTO response = fakeStoreProductClient.getProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        Assertions.assertEquals(response, fakeStoreProductDTO);
    }

    @Test
    public void testGetProductByIdThrowsExceptionWhenClientReturnsNull(){
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntityMock =
                new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplateMock.getForEntity(any(String.class), eq(FakeStoreProductDTO.class), any(String.class)))
                .thenReturn(fakeStoreProductDTOResponseEntityMock);

        Assertions.assertThrows(NotFoundException.class, ()->{
            fakeStoreProductClient.getProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        });
    }


    @Test
    public void testDeleteProductByIdReturnsCorrectResponse() throws NotFoundException {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplate);

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        fakeStoreProductDTO.setTitle("Real Fruit Juice");
        fakeStoreProductDTO.setDescription("Fresh Fruit Juice!");
        fakeStoreProductDTO.setCategory("Food");
        fakeStoreProductDTO.setPrice(110.0);
        fakeStoreProductDTO.setImage("www.real.com");

        ResponseEntity<FakeStoreProductDTO> responseMock = new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);
        when(restTemplate.exchange(any(String.class), any(HttpMethod.class), any(), eq(FakeStoreProductDTO.class), any(String.class)))
                .thenReturn(responseMock);

        FakeStoreProductDTO response = fakeStoreProductClient.deleteProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        Assertions.assertEquals(fakeStoreProductDTO, response);
        Assertions.assertEquals(fakeStoreProductDTO.getId(), response.getId());
    }

    @Test
    public void testDeleteProductByIdWhenClientReturnsNotFoundException() throws NotFoundException {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntityMock =
                new ResponseEntity<>(null, HttpStatus.OK);

        when(restTemplateMock.exchange(any(String.class), any(HttpMethod.class),
                any(), eq(FakeStoreProductDTO.class), any(String.class))).thenReturn(fakeStoreProductDTOResponseEntityMock);

        Assertions.assertThrows(NotFoundException.class, ()->{
           fakeStoreProductClient.deleteProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        });
    }

    @Test
    public void testCreateProduct(){
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r7");
        fakeStoreProductDTO.setTitle("Test-Product");
        fakeStoreProductDTO.setImage("www.product-test.com");
        fakeStoreProductDTO.setCategory("Test");
        fakeStoreProductDTO.setPrice(100.0);
        fakeStoreProductDTO.setDescription("Testing create product.");

        GenericProductDTO genericProductDTORequest = new GenericProductDTO();
        genericProductDTORequest.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r7");
        genericProductDTORequest.setTitle("Test-Product");
        genericProductDTORequest.setImage("www.product-test.com");
        genericProductDTORequest.setCategory("Test");
        genericProductDTORequest.setPrice(100.0);
        genericProductDTORequest.setDescription("Testing create product.");

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntityMock =
                new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);

        when(restTemplateMock.postForEntity(any(String.class),any(GenericProductDTO.class),
                eq(FakeStoreProductDTO.class))).thenReturn(fakeStoreProductDTOResponseEntityMock);

        FakeStoreProductDTO fakeStoreResponse = fakeStoreProductClient.createProduct(genericProductDTORequest);

        Assertions.assertEquals(genericProductDTORequest.getId(), fakeStoreResponse.getId());
        Assertions.assertEquals(genericProductDTORequest.getPrice(), fakeStoreResponse.getPrice());
        Assertions.assertEquals(genericProductDTORequest.getCategory(), fakeStoreResponse.getCategory());
        Assertions.assertEquals(genericProductDTORequest.getTitle(), fakeStoreResponse.getTitle());
        Assertions.assertEquals(genericProductDTORequest.getImage(), fakeStoreResponse.getImage());
        Assertions.assertEquals(genericProductDTORequest.getDescription(), fakeStoreResponse.getDescription());
    }


    @Test
    public void testUpdateProductByIdReturnsCorrectResponse(){
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        fakeStoreProductDTO.setTitle("Test-Product-Update");
        fakeStoreProductDTO.setDescription("Testing Update Product By Id Returns Correct Response");
        fakeStoreProductDTO.setImage("www.test.com");
        fakeStoreProductDTO.setCategory("Test");
        fakeStoreProductDTO.setPrice(100.0);

        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntityMock =new ResponseEntity<>(fakeStoreProductDTO, HttpStatus.OK);

        when(restTemplateMock.exchange(any(String.class), any(HttpMethod.class), any(HttpEntity.class), eq(FakeStoreProductDTO.class)
                ,any(String.class))).thenReturn(fakeStoreProductDTOResponseEntityMock);

        GenericProductDTO genericProductDTORequest = new GenericProductDTO();
        genericProductDTORequest.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        genericProductDTORequest.setTitle("Test-Product-Update");
        genericProductDTORequest.setDescription("Testing Update Product By Id Returns Correct Response");
        genericProductDTORequest.setCategory("Test");
        genericProductDTORequest.setImage("www.test.com");
        genericProductDTORequest.setPrice(100.0);

        FakeStoreProductDTO fakeStoreProductResponse = fakeStoreProductClient.updateProductById(genericProductDTORequest, "9fb4ddf9-de23-4002-99f9-59d28c3164r4");

        Assertions.assertEquals("9fb4ddf9-de23-4002-99f9-59d28c3164r4", fakeStoreProductResponse.getId());
        Assertions.assertEquals("Test-Product-Update", fakeStoreProductResponse.getTitle());
        Assertions.assertEquals("Testing Update Product By Id Returns Correct Response", fakeStoreProductResponse.getDescription());
        Assertions.assertEquals("Test", fakeStoreProductResponse.getCategory());
        Assertions.assertEquals("www.test.com", fakeStoreProductResponse.getImage());
        Assertions.assertEquals(100.0, fakeStoreProductResponse.getPrice());
    }

    @Test
    public void testGetAllProductsReturnsCorrectResponse() throws NotFoundException {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);
        FakeStoreProductDTO fakeStoreProductDTO1 = new FakeStoreProductDTO();
        fakeStoreProductDTO1.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        fakeStoreProductDTO1.setTitle("Test-Product-Get-All");
        fakeStoreProductDTO1.setDescription("Testing Get All Products Returns Correct Response");
        fakeStoreProductDTO1.setImage("www.test.com");
        fakeStoreProductDTO1.setCategory("Test");
        fakeStoreProductDTO1.setPrice(100.0);

        FakeStoreProductDTO fakeStoreProductDTO2 = new FakeStoreProductDTO();
        fakeStoreProductDTO2.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        fakeStoreProductDTO2.setTitle("Test-Product-Get-All");
        fakeStoreProductDTO2.setDescription("Testing Get All products Returns Correct Response");
        fakeStoreProductDTO2.setImage("www.test.com");
        fakeStoreProductDTO2.setPrice(200.0);
        fakeStoreProductDTO2.setCategory("Test");

        FakeStoreProductDTO[] fakeStoreProductDTOS = new FakeStoreProductDTO[2];
        fakeStoreProductDTOS[0] = fakeStoreProductDTO1;
        fakeStoreProductDTOS[1] = fakeStoreProductDTO2;
        ResponseEntity<FakeStoreProductDTO[]> fakeResponseEntityMock = new ResponseEntity<>(fakeStoreProductDTOS, HttpStatus.OK);

        when(restTemplateMock.getForEntity(any(String.class), eq(FakeStoreProductDTO[].class))).thenReturn(fakeResponseEntityMock);

        List<FakeStoreProductDTO> fakeStoreProductDTOResponse = fakeStoreProductClient.getAllProducts();
        Assertions.assertEquals(fakeStoreProductDTOS[0], fakeStoreProductDTOResponse.get(0));
        Assertions.assertEquals(fakeStoreProductDTOS[1], fakeStoreProductDTOResponse.get(1));
    }

    @Test
    public void testGetAllProductThrowNotFoundExceptionWhenClientReturnsNull() {
        RestTemplate restTemplateMock = Mockito.mock(RestTemplate.class);
        when(restTemplateBuilderMock.build()).thenReturn(restTemplateMock);

        ResponseEntity<FakeStoreProductDTO[]> fakeStoreResponseMock = new ResponseEntity<>(null, HttpStatus.OK);
        when(restTemplateMock.getForEntity(any(String.class), eq(FakeStoreProductDTO[].class))).thenReturn(fakeStoreResponseMock);

        Assertions.assertThrows(NotFoundException.class, ()-> fakeStoreProductClient.getAllProducts());

    }
}
