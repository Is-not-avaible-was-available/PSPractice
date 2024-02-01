package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.CategoryDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Repositories.CategoryRepository;
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
public class CategoryServiceTest {
    @Autowired
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepositoryMock;

    @Test
    public void testGetCategoryByIdReturnsCorrectResponse() throws NotFoundException {
        Category category = new Category();
        UUID random = UUID.randomUUID();
        category.setUuid(random);
        category.setName("Electronics");

        Optional<Category> optionalCategory = Optional.of(category);
        when(categoryRepositoryMock.findById(any(UUID.class))).thenReturn(optionalCategory);
        
        Category categoryResponse = categoryService.getCategoryById(random.toString());

        Assertions.assertEquals(category.getUuid().toString(), categoryResponse.getUuid().toString());
        Assertions.assertEquals(category.getName(), categoryResponse.getName());
        
    }
}
