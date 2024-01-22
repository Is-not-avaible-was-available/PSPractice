package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.CategoryDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.AlreadyExistsException;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Mappers.DTOMappers;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("categoryService")
public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(String id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }
        return categoryOptional.get();
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category deleteCategoryById(String id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }
        categoryRepository.deleteById(UUID.fromString(id));
        return categoryOptional.get();
    }

    public Category updateCategoryById(String id, CategoryDTO categoryDTO) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));

        if(categoryOptional.isEmpty()){
            throw new NotFoundException("Category with id:"+id+", is not found!");
        }

        Category category = categoryOptional.get();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    public Category createCategory(CategoryDTO categoryDTO) throws AlreadyExistsException {
        Optional<Category> categoryOptional = categoryRepository.findByName(categoryDTO.getName());
        if(categoryOptional.isPresent()){
            throw new AlreadyExistsException("Already present!");
        }
        Category category = new Category();
        category.setName(categoryDTO.getName());
        return categoryRepository.save(category);
    }

    public List<GenericProductDTO> getAllProductsByCategoryId(String id) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString(id));
        if(categoryOptional.isEmpty()){
            throw new NotFoundException("category not found!");
        }

        Category category = categoryOptional.get();
        List<Product> products = category.getProducts();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Product product:products){
            genericProductDTOS.add(DTOMappers.productToGenericProductDTO(product));
        }
        return genericProductDTOS;
    }

    public List<GenericProductDTO> getAllProductsByCategoryIds(List<String> categoryIds){
        List<UUID> catUuids = categoryIds.stream().map(UUID::fromString).toList();
        List<Category> categories = categoryRepository.findAllById(catUuids);
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(Category category: categories){
            for(Product product: category.getProducts()){
                genericProductDTOS.add(DTOMappers.productToGenericProductDTO(product));
            }
        }
        return genericProductDTOS;
    }
}
