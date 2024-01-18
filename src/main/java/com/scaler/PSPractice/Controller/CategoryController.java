package com.scaler.PSPractice.Controller;

import com.scaler.PSPractice.Exception.NotFoundException;
import com.scaler.PSPractice.Services.CategoryService;
import com.scaler.PSPractice.models.Category;
import com.scaler.PSPractice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;
    @Autowired
    public CategoryController(@Qualifier("categoryService") CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable("id") String id) throws NotFoundException {
        return categoryService.getCategoryById(id);
    }
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{uuid}")
    public List<Product> getProductsByCategory(@PathVariable("uuid") String  uuid) throws NotFoundException {
        return categoryService.getProductByCategory(uuid);
    }
}
