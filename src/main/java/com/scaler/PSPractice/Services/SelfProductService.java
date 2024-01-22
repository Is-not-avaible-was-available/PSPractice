package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Mappers.DTOMappers;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Models.Price;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.Repositories.CategoryRepository;
import com.scaler.PSPractice.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }



    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));

        if(optionalProduct.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return DTOMappers.productToGenericProductDTO(optionalProduct.get());
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<Product> products =  productRepository.findAll();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(Product product:products){
            genericProductDTOS.add(DTOMappers.productToGenericProductDTO(product));
        }
        return genericProductDTOS;

    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Product product = new Product();
        Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDTO.getCategory());
        if(optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(genericProductDTO.getCategory());
            Category saved = categoryRepository.save(category);
            product.setCategory(saved);
        }else{
            product.setCategory(optionalCategory.get());
        }
        Price price = new Price();
        price.setPrice(genericProductDTO.getPrice());
        price.setCurrency("Rupee");
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        product.setPrice(price);
        Product savedProduct = productRepository.save(product);
        return DTOMappers.productToGenericProductDTO(savedProduct);
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){

            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = optionalProduct.get();
        Optional<Category> optionalCategory = categoryRepository.findByName(genericProductDTO.getCategory());
        if(optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(genericProductDTO.getCategory());
            Category saved = categoryRepository.save(category);
            product.setCategory(saved);
        }else{
            product.setCategory(optionalCategory.get());
        }
        Price price = new Price();
        price.setPrice(genericProductDTO.getPrice());
        product.setPrice(price);
        product.setTitle(genericProductDTO.getTitle());
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        Product savedProduct = productRepository.save(product);
        return DTOMappers.productToGenericProductDTO(savedProduct);
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        productRepository.deleteById(UUID.fromString(id));
        return DTOMappers.productToGenericProductDTO(optionalProduct.get());
    }
}
