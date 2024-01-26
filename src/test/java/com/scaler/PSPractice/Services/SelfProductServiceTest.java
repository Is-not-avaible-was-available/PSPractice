package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Repositories.CategoryRepository;
import com.scaler.PSPractice.Repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.NotActiveException;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class SelfProductServiceTest {

    private SelfProductService selfProductService;
    private ProductRepository productRepositoryMock;
    private CategoryRepository categoryRepositoryMock;

    public SelfProductServiceTest(){
        this.productRepositoryMock = Mockito.mock(ProductRepository.class);
        this.categoryRepositoryMock = Mockito.mock(CategoryRepository.class);
        this.selfProductService = new SelfProductService(productRepositoryMock, categoryRepositoryMock);
    }

    @Test
    public void testGetProductByIdThrowNotFoundExceptionWhenRepositoryReturnsNull() throws NotFoundException {
//        when(productRepositoryMock.findById(any(UUID.class))).thenReturn(null);
//
//        GenericProductDTO response = selfProductService.getProductById("80cea88c-5034-4e65-8146-32359de6447f");
//        Assertions.assertNull(response);
//        Assertions.assertThrows(NotActiveException.class, ()-> selfProductService.getProductById("80cea88c-5034-4e65-8146-32359de6447f"));
    }

}
