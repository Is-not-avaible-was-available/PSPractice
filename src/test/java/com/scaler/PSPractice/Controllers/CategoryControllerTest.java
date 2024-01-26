package com.scaler.PSPractice.Controllers;

import com.scaler.PSPractice.DTOs.CategoryDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.AlreadyExistsException;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Services.CategoryService;
import org.aspectj.weaver.ast.Call;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryControllerTest {
    private CategoryController categoryController;
    private CategoryService categoryService;

    public CategoryControllerTest(){
        this.categoryService = Mockito.mock(CategoryService.class);
        this.categoryController = new CategoryController(categoryService);
    }

    @Test
    public void testGetCategoryByIdReturnsCorrectResponse() throws NotFoundException {
        Category category = new Category();
        category.setName("Food");
        category.setUuid(UUID.fromString("80cea88c-5034-4e65-8146-32359de6447f"));

        when(categoryService.getCategoryById(any(String.class))).thenReturn(category);

        CategoryDTO response = categoryController.getCategoryById("80cea88c-5034-4e65-8146-32359de6447f");
        Assertions.assertEquals("Food", response.getName());
        Assertions.assertEquals("80cea88c-5034-4e65-8146-32359de6447f", response.getId());
    }

    @Test
    public void testGetAllCategoriesReturnsCorrectResponse(){
        UUID uuid1 = UUID.randomUUID();
        UUID uuid2 = UUID.randomUUID();
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category();
        category1.setName("Foods");
        category1.setUuid(uuid1);

        Category category2 = new Category();
        category2.setName("Electronics");
        category2.setUuid(uuid2);

        categories.add(category1);
        categories.add(category2);

        when(categoryService.getAllCategories()).thenReturn(categories);

        List<CategoryDTO> response = categoryController.getAllCategories();

        Assertions.assertEquals(categories.get(0).getName(), response.get(0).getName());
        Assertions.assertEquals(uuid1.toString(), response.get(0).getId());
        Assertions.assertEquals(categories.get(1).getName(), response.get(1).getName());
        Assertions.assertEquals(uuid2.toString(), response.get(1).getId());
    }

    @Test
    public void testCreateCategoryReturnsCorrectResponse() throws AlreadyExistsException {
        UUID uuid1 = UUID.randomUUID();
        Category category = new Category();
        category.setUuid(uuid1);
        category.setName("Fashion");

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName("Fashion");
        categoryDTO.setId("80cea88c-5034-4e65-8146-32359de6447f");

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(category);

        CategoryDTO response = categoryController.createCategory(categoryDTO);

        Assertions.assertEquals(category.getName(), response.getName());
        Assertions.assertEquals(category.getUuid().toString(), response.getId());
    }

    @Test
    public void testUpdateCategoryByIdReturnsCorrectResponse() throws NotFoundException {
        Category category = new Category();
        UUID uuid1 = UUID.randomUUID();
        category.setUuid(uuid1);
       category.setName("Lifestyle");

       CategoryDTO categoryDTO = new CategoryDTO();
       categoryDTO.setId("80cea88c-5034-4e65-8146-32359de6447f");
       category.setName("Lifestyle");

       when(categoryService.updateCategoryById(any(String.class), any(CategoryDTO.class))).thenReturn(category);

       CategoryDTO response = categoryController.updateCategoryById("80cea88c-5034-4e65-8146-32359de6447f"
               ,categoryDTO);

       Assertions.assertEquals(uuid1.toString(), response.getId());
       Assertions.assertEquals(category.getName(), response.getName());
    }
    @Test
    public void testDeleteCategoryByIdReturnsCorrectResponse() throws NotFoundException {
        Category category = new Category();
        UUID uuid = UUID.randomUUID();
        category.setUuid(uuid);
        category.setName("Underwear");

        when(categoryService.deleteCategoryById(any(String.class))).thenReturn(category);

        CategoryDTO categoryDTOResponse = categoryController
                .deleteCategoryById("80cea88c-5034-4e65-8146-32359de6447f");

        Assertions.assertEquals(uuid.toString(), categoryDTOResponse.getId());
        Assertions.assertEquals("Underwear", categoryDTOResponse.getName());
    }
    @Test
    public void testGetProductsByCategoryIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDTO genericProductDTO1 = new GenericProductDTO();
        genericProductDTO1.setId("80cea88c-5034-4e65-8146-32359de6447f");
        genericProductDTO1.setTitle("Iphone 6");
        genericProductDTO1.setCategory("Electronics");
        genericProductDTO1.setImage("www.iphone.com");
        genericProductDTO1.setDescription("Best Phone Ever!");
        genericProductDTO1.setPrice(155000.0);

        GenericProductDTO genericProductDTO2 = new GenericProductDTO();
        genericProductDTO2.setId("80cea88c-5034-4e65-8146-32359de6440r");
        genericProductDTO2.setTitle("Iphone 7");
        genericProductDTO2.setCategory("Electronics");
        genericProductDTO2.setImage("www.iphone.com");
        genericProductDTO2.setDescription("Best Phone Ever!!");
        genericProductDTO2.setPrice(140000.0);

        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        genericProductDTOS.add(genericProductDTO1);
        genericProductDTOS.add(genericProductDTO2);

        when(categoryService.getAllProductsByCategoryId(any(String.class)))
                .thenReturn(genericProductDTOS);

        List<GenericProductDTO> response = categoryController
                .getProductsByCategoryId("80cea88c-5034-4e65-8146-32359de6220r");

        Assertions.assertEquals(genericProductDTOS, response);
    }

    @Test
    public void testGetProductsByCategoryIdsReturnCorrectResponse(){
        GenericProductDTO genericProductDTO1 = new GenericProductDTO();
        genericProductDTO1.setId("80cea88c-5034-4e65-8146-32359de6447f");
        genericProductDTO1.setTitle("Iphone 6");
        genericProductDTO1.setCategory("Electronics");
        genericProductDTO1.setImage("www.iphone.com");
        genericProductDTO1.setDescription("Best Phone Ever!");
        genericProductDTO1.setPrice(155000.0);

        GenericProductDTO genericProductDTO2 = new GenericProductDTO();
        genericProductDTO2.setId("80cea88c-5034-4e65-8146-32359de6440r");
        genericProductDTO2.setTitle("Iphone 7");
        genericProductDTO2.setCategory("Electronics");
        genericProductDTO2.setImage("www.iphone.com");
        genericProductDTO2.setDescription("Best Phone Ever!!");
        genericProductDTO2.setPrice(140000.0);

        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        genericProductDTOS.add(genericProductDTO1);
        genericProductDTOS.add(genericProductDTO2);

        List<String> categoryIds = new ArrayList<>();
        categoryIds.add("80cea88c-5034-4e65-8146-32359de4240r");
        categoryIds.add("80cea88c-5034-4e65-8146-32359ed6441p");
        when(categoryService.getAllProductsByCategoryIds(any())).thenReturn(genericProductDTOS);

        List<GenericProductDTO> genericProductDTOSResponse = categoryController.getAllProductsByCategories(categoryIds);

        Assertions.assertEquals(genericProductDTOS, genericProductDTOSResponse);
    }
}
