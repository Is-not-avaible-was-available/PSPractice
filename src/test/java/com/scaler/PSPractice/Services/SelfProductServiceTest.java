package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Models.Price;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.Repositories.CategoryRepository;
import com.scaler.PSPractice.Repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SelfProductServiceTest {
    @Autowired
    private SelfProductService selfProductService;
    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private CategoryRepository categoryRepository;

    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        Product  product =new Product();
        UUID random = UUID.randomUUID();
        product.setUuid(random);
        product.setTitle("iPhone15");
        product.setDescription("Best Phone Ever!");
        product.setPrice(new Price());
        product.setImage("www.iphone.com");
        product.setCategory(new Category());


        Optional<Product> productOptional = Optional.of(product);

        when(productRepository.findById(any(UUID.class))).thenReturn(productOptional);
        GenericProductDTO response = selfProductService.getProductById(random.toString());

        Assertions.assertEquals(product.getUuid().toString(), response.getId());
        Assertions.assertEquals(product.getDescription(), response.getDescription());
        Assertions.assertEquals(product.getTitle(), response.getTitle());
    }
    @Test
    public void testGetProductByIdThrowsNotFoundExceptionWhenRepositoryReturnsNull(){
        UUID random = UUID.randomUUID();
        when(productRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, ()->{
            selfProductService.getProductById(random.toString());
        });
    }
}
