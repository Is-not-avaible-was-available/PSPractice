package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.ThirdParty.FakeStoreProductClient;
import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class FakeStoreProductServiceTest {
    private FakeStoreProductService fakeStoreProductService;
    private FakeStoreProductClient fakeStoreProductClientMock;

    public FakeStoreProductServiceTest(){
        this.fakeStoreProductClientMock = Mockito.mock(FakeStoreProductClient.class);
        this.fakeStoreProductService = new FakeStoreProductService(fakeStoreProductClientMock);
    }


//    @Test
//    public void testGetProductByIdWhenClientReturnsNull() throws NotFoundException {
//
//        when(fakeStoreProductClientMock.getProductById(any(String.class))).thenReturn(null);
//        GenericProductDTO response = fakeStoreProductService.getProductById("7db4ddf9-de23-4002-99f9-59d28c3164b9");
//        Assertions.assertNull(response);
//    }

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        fakeStoreProductDTO.setTitle("Godrej Washing Machine");
        fakeStoreProductDTO.setCategory("Electronics");
        fakeStoreProductDTO.setDescription("Best washing machine");
        fakeStoreProductDTO.setPrice(16000.0);
        fakeStoreProductDTO.setImage("www.godrej.com");
        when(fakeStoreProductClientMock.getProductById(any(String.class))).thenReturn(fakeStoreProductDTO);


        GenericProductDTO genericProductDTO = fakeStoreProductService.getProductById("7db4ddf9-de23-4002-99f9-59d28c3164b9");

        Assertions.assertEquals(fakeStoreProductDTO.getId(), genericProductDTO.getId());
        Assertions.assertEquals(fakeStoreProductDTO.getTitle(), genericProductDTO.getTitle());
        Assertions.assertEquals(fakeStoreProductDTO.getDescription(), genericProductDTO.getDescription());
        Assertions.assertEquals(fakeStoreProductDTO.getImage(), genericProductDTO.getImage());
        Assertions.assertEquals(fakeStoreProductDTO.getCategory(), genericProductDTO.getCategory());
        Assertions.assertEquals(fakeStoreProductDTO.getPrice(), genericProductDTO.getPrice());
    }

    @Test
    public void testCreateProductReturnsCorrectResponse(){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("8eb4ddf9-de23-4002-99f9-59d28c3164d1");
        fakeStoreProductDTO.setImage("www.namkeen.com");
        fakeStoreProductDTO.setTitle("Sev Bhujia");
        fakeStoreProductDTO.setDescription("Crispy!");
        fakeStoreProductDTO.setCategory("Food");
        fakeStoreProductDTO.setPrice(340.0);

        when(fakeStoreProductClientMock.createProduct(any(GenericProductDTO.class))).thenReturn(fakeStoreProductDTO);
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId("8eb4ddf9-de23-4002-99f9-59d28c3164d1");
        genericProductDTO.setImage("www.namkeen.com");
        genericProductDTO.setTitle("Sev Bhujia");
        genericProductDTO.setDescription("Crispy!");
        genericProductDTO.setCategory("Food");
        genericProductDTO.setPrice(340.0);

        GenericProductDTO response = fakeStoreProductService.createProduct(genericProductDTO);

        Assertions.assertEquals(response.getId(), fakeStoreProductDTO.getId());
        Assertions.assertEquals(response.getTitle(), fakeStoreProductDTO.getTitle());
        Assertions.assertEquals(response.getDescription(), fakeStoreProductDTO.getDescription());
        Assertions.assertEquals(response.getPrice(), fakeStoreProductDTO.getPrice());
        Assertions.assertEquals(response.getCategory(), fakeStoreProductDTO.getCategory());
        Assertions.assertEquals(response.getImage(), fakeStoreProductDTO.getImage());
    }

    @Test
    public void testDeleteProductByIdReturnCorrectResponse() throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164d0");
        fakeStoreProductDTO.setTitle("Amul milk");
        fakeStoreProductDTO.setDescription("Pure Milk!");
        fakeStoreProductDTO.setImage("www.amul.com");
        fakeStoreProductDTO.setPrice(66.0);
        fakeStoreProductDTO.setCategory("Everyday");

        when(fakeStoreProductClientMock.deleteProductById(any(String.class))).thenReturn(fakeStoreProductDTO);
        GenericProductDTO response = fakeStoreProductService.deleteProductById("9fb4ddf9-de23-4002-99f9-59d28c3164d0");

        Assertions.assertEquals(response.getId(), fakeStoreProductDTO.getId());
        Assertions.assertEquals(response.getTitle(), fakeStoreProductDTO.getTitle());
        Assertions.assertEquals(response.getDescription(), fakeStoreProductDTO.getDescription());
        Assertions.assertEquals(response.getImage(), fakeStoreProductDTO.getImage());
        Assertions.assertEquals(response.getCategory(),fakeStoreProductDTO.getCategory());
        Assertions.assertEquals(response.getPrice(), fakeStoreProductDTO.getPrice());
    }

    @Test
    public void testUpdateProductByIdReturnsCorrectResponse(){
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        fakeStoreProductDTO.setTitle("Test-Update-Product");
        fakeStoreProductDTO.setDescription("Testing FakeStoreService UpdateProductById");
        fakeStoreProductDTO.setImage("www.test.com");
        fakeStoreProductDTO.setCategory("Test");
        fakeStoreProductDTO.setPrice(1000.0);

        when(fakeStoreProductClientMock.updateProductById(any(GenericProductDTO.class), any(String.class))).thenReturn(fakeStoreProductDTO);

        //RequestDTO can be anything, because actual client method will not get called and this DTO will not be passed a request.
        //the above fakeStoreProductDTO is returned from the mock client and the main service method also returns the same content as above fakeStoreProductDTO
        GenericProductDTO genericProductDTORequest = new GenericProductDTO();
        genericProductDTORequest.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r4");
        genericProductDTORequest.setTitle("Test-Update-Product");
        genericProductDTORequest.setDescription("Testing FakeStoreService UpdateProductById");
        genericProductDTORequest.setCategory("Test");
        genericProductDTORequest.setImage("www.test.com");
        genericProductDTORequest.setPrice(1000.0);

        GenericProductDTO response = fakeStoreProductService.updateProductById("9fb4ddf9-de23-4002-99f9-59d28c3164r4", genericProductDTORequest);

        Assertions.assertEquals("9fb4ddf9-de23-4002-99f9-59d28c3164r4", response.getId());
        Assertions.assertEquals("Test-Update-Product", response.getTitle());
        Assertions.assertEquals("Testing FakeStoreService UpdateProductById", response.getDescription());
        Assertions.assertEquals("Test", response.getCategory());
        Assertions.assertEquals(1000.0, response.getPrice());
        Assertions.assertEquals("www.test.com", response.getImage());
    }

    @Test
    public void testGetAllProductReturnsCorrectResponse() throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = new FakeStoreProductDTO();
        fakeStoreProductDTO.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r9");
        fakeStoreProductDTO.setTitle("Test-Get-All");
        fakeStoreProductDTO.setDescription("Test FakeStoreProduct Service GetAllProducts");
        fakeStoreProductDTO.setImage("www.test.com");
        fakeStoreProductDTO.setCategory("Test");
        fakeStoreProductDTO.setPrice(1000.0);

        FakeStoreProductDTO fakeStoreProductDTO2 = new FakeStoreProductDTO();
        fakeStoreProductDTO2.setId("9fb4ddf9-de23-4002-99f9-59d28c3164r0");
        fakeStoreProductDTO2.setTitle("Test-Get-All");
        fakeStoreProductDTO2.setDescription("Test FakeStoreProduct Service GetAllProducts");
        fakeStoreProductDTO2.setImage("www.test.com");
        fakeStoreProductDTO2.setCategory("Test");
        fakeStoreProductDTO2.setPrice(2000.0);

        List<FakeStoreProductDTO> fakeStoreProductDTOS = new ArrayList<>();
        fakeStoreProductDTOS.add(fakeStoreProductDTO);
        fakeStoreProductDTOS.add(fakeStoreProductDTO2);

        when(fakeStoreProductClientMock.getAllProducts()).thenReturn(fakeStoreProductDTOS);

        List<GenericProductDTO> genericProductDTOSResponse = fakeStoreProductService.getAllProducts();

        Assertions.assertEquals(fakeStoreProductDTO.getId(), genericProductDTOSResponse.get(0).getId());
        Assertions.assertEquals(fakeStoreProductDTO.getTitle(), genericProductDTOSResponse.get(0).getTitle());
        Assertions.assertEquals(fakeStoreProductDTO2.getId(), genericProductDTOSResponse.get(1).getId());
        Assertions.assertEquals(fakeStoreProductDTO2.getTitle(), genericProductDTOSResponse.get(1).getTitle());

    }
}
