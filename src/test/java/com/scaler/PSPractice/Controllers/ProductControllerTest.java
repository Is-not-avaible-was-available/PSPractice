package com.scaler.PSPractice.Controllers;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.Services.FakeStoreProductService;
import com.scaler.PSPractice.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductControllerTest {

    private ProductController productController;


    private ProductService productServiceMock;
    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;


    public ProductControllerTest(){

        this.productServiceMock = Mockito.mock(FakeStoreProductService.class);
        this.productController = new ProductController(productServiceMock);
    }
//    @Test
//    public void testGetProductByIdReturnsEmptyObjectWhenProductNotFound() throws NotFoundException {
//        when(productServiceMock.getProductById(any(String.class))).thenReturn(null);
//
//        GenericProductDTO response = productController.getProductById(any(String.class));
//        Assertions.assertNotNull(response);
//    }

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        genericProductDTO.setTitle("Iphone");

        when(productServiceMock.getProductById(any(String.class))).thenReturn(genericProductDTO);

        GenericProductDTO response = productController.getProductById("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        Assertions.assertEquals(response.getId(), "7db4ddf9-de23-4002-99f9-59d28c3164b9", "id is different");
        Assertions.assertEquals(response.getTitle(), "Iphone", "title is different");
    }

    @Test
    public void testDeleteProductByIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        genericProductDTO.setTitle("Wrangler Jeans");
        genericProductDTO.setDescription("Best Ever Jeans!");
        genericProductDTO.setCategory("Fashion");
        genericProductDTO.setPrice(2000);
        genericProductDTO.setImage("www.wrangler.com");

        when(productServiceMock.deleteProductById(any(String.class))).thenReturn(genericProductDTO);

        GenericProductDTO response = productController.deleteProductById("7db4ddf9-de23-4002-99f9-59d28c3164b9");

        Assertions.assertEquals(response.getId(), "7db4ddf9-de23-4002-99f9-59d28c3164b9", "id is different");
        Assertions.assertEquals(response.getTitle(), "Wrangler Jeans", " title is different");
        Assertions.assertEquals(response.getDescription(), "Best Ever Jeans!", "description is different");
        Assertions.assertEquals(response.getCategory(), "Fashion", "category is different");
        Assertions.assertEquals(response.getPrice(), 2000, "price is different");
        Assertions.assertEquals(response.getImage(), "www.wrangler.com", "image is different");
    }
    @Test
    public void testCreateProductByIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        genericProductDTO.setTitle("Wrangler Jeans");
        genericProductDTO.setDescription("Best Ever Jeans!");
        genericProductDTO.setCategory("Fashion");
        genericProductDTO.setPrice(2000);
        genericProductDTO.setImage("www.wrangler.com");

        when(productServiceMock.createProduct(any(GenericProductDTO.class))).thenReturn(genericProductDTO);

        GenericProductDTO response = productController.createProduct(genericProductDTO);

        Assertions.assertEquals(response.getId(), "7db4ddf9-de23-4002-99f9-59d28c3164b9", "id is different");
        Assertions.assertEquals(response.getTitle(), "Wrangler Jeans", " title is different");
        Assertions.assertEquals(response.getDescription(), "Best Ever Jeans!", "description is different");
        Assertions.assertEquals(response.getCategory(), "Fashion", "category is different");
        Assertions.assertEquals(response.getPrice(), 2000, "price is different");
        Assertions.assertEquals(response.getImage(), "www.wrangler.com", "image is different");
    }

    @Test
    public void testUpdateProductByIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        genericProductDTO.setTitle("Wrangler Shirt");
        genericProductDTO.setDescription("Best Ever Shirt!");
        genericProductDTO.setCategory("Fashion");
        genericProductDTO.setPrice(1000);
        genericProductDTO.setImage("www.wrangler.com");

        when(productServiceMock.updateProductById(any(String.class), any(GenericProductDTO.class)))
                .thenReturn(genericProductDTO);

        GenericProductDTO response = productController.updateProductById( genericProductDTO,
                "7db4ddf9-de23-4002-99f9-59d28c3164b9");

        Assertions.assertEquals(response.getId(), "7db4ddf9-de23-4002-99f9-59d28c3164b9");
        Assertions.assertEquals(response.getTitle(), "Wrangler Shirt");
        Assertions.assertEquals(response.getDescription(),"Best Ever Shirt!");
        Assertions.assertEquals(response.getCategory(), "Fashion");
        Assertions.assertEquals(response.getPrice(), 1000);
        Assertions.assertEquals(response.getImage(), "www.wrangler.com");

    }

    @Test
    public void testGetAllProductsReturnsCorrectResponse() throws NotFoundException {
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        GenericProductDTO wranglerShirt = new GenericProductDTO();
        wranglerShirt.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        wranglerShirt.setTitle("Wrangler Shirt");
        wranglerShirt.setDescription("Best Ever Shirt!");
        wranglerShirt.setCategory("Fashion");
        wranglerShirt.setPrice(1000);
        wranglerShirt.setImage("www.wrangler.com");
        genericProductDTOS.add(wranglerShirt);

        GenericProductDTO wranglerJeans = new GenericProductDTO();
        wranglerJeans.setId("7db4ddf9-de23-4002-99f9-59d28c3164b9");
        wranglerJeans.setTitle("Wrangler Jeans");
        wranglerJeans.setDescription("Best Ever Jeans!");
        wranglerJeans.setCategory("Fashion");
        wranglerJeans.setPrice(2000);
        wranglerJeans.setImage("www.wrangler.com");
        genericProductDTOS.add(wranglerJeans);

        when(productServiceMock.getAllProducts()).thenReturn(genericProductDTOS);
        List<GenericProductDTO> response = productController.getAllProducts();

        Assertions.assertEquals(genericProductDTOS, response);

    }


}
