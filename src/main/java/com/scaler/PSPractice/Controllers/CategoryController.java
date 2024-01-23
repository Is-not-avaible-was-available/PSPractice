package com.scaler.PSPractice.Controllers;

import com.scaler.PSPractice.DTOs.CategoryDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.AlreadyExistsException;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Mappers.DTOMappers;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.Services.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(@Qualifier("categoryService") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public CategoryDTO getCategoryById(@PathVariable("id")String id) throws NotFoundException {
        return DTOMappers.categoryToCategoryResponseDTO(categoryService.getCategoryById(id));
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for(Category category:categories){
            categoryDTOS.add(DTOMappers.categoryToCategoryResponseDTO(category));
        }
        return categoryDTOS;
    }

    @DeleteMapping("/{id}")
    public CategoryDTO deleteCategoryById(@PathVariable("id") String id) throws NotFoundException {
        return DTOMappers.categoryToCategoryResponseDTO(categoryService.deleteCategoryById(id));
    }

    @PutMapping("/{id}")
    public CategoryDTO updateCategoryById(@PathVariable("id")String id,
                                          @RequestBody CategoryDTO categoryDTO) throws NotFoundException {
        return DTOMappers.categoryToCategoryResponseDTO(categoryService.updateCategoryById(id, categoryDTO));
    }

    @PostMapping
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) throws AlreadyExistsException {
        return DTOMappers.categoryToCategoryResponseDTO(categoryService.createCategory(categoryDTO));
    }

    @GetMapping("/products/{id}")
    public List<GenericProductDTO> getProductsByCategoryId(@PathVariable("id")String id) throws NotFoundException {
       return categoryService.getAllProductsByCategoryId(id);
    }

    @GetMapping("/products")
    public List<GenericProductDTO> getAllProductsByCategories(@RequestBody List<String> categoryIds){
        return categoryService.getAllProductsByCategoryIds(categoryIds);
    }

}
