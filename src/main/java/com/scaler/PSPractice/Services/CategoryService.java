package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.Exception.NotFoundException;
import com.scaler.PSPractice.Repositories.CategoryRepository;
import com.scaler.PSPractice.models.Category;
import com.scaler.PSPractice.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("categoryService")
public class CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(String id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("category with id:"+id+", is not found!");
        }
        return optionalCategory.get();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Transactional
    public List<Product> getProductByCategory(String id) throws NotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(UUID.fromString(id));
        if(optionalCategory.isEmpty()){
            throw new NotFoundException("category with id:"+id+", is not found!");
        }
        Category category = optionalCategory.get();
        return category.getProducts();
    }
}
