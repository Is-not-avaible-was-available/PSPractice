package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.Exception.NotFoundException;
import com.scaler.PSPractice.Mapper.DTOMapper;
import com.scaler.PSPractice.Repositories.ProductRepository;
import com.scaler.PSPractice.models.Category;
import com.scaler.PSPractice.models.Price;
import com.scaler.PSPractice.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;

    public SelfProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @Override
    public GenericProductDTO findProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = optionalProduct.get();
        return DTOMapper.productToGenericProductDTO(product);
    }

    @Override
    public List<GenericProductDTO> findAllProducts() throws NotFoundException {
        List<Product> products = productRepository.findAll();
        List<GenericProductDTO>genericProductDTOS = new ArrayList<>();
        for(Product product:  products){
            genericProductDTOS.add(DTOMapper.productToGenericProductDTO(product));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        Product product = new Product();
        product.setCategory(new Category(genericProductDTO.getCategory()));
        product.setDescription(genericProductDTO.getDescription());
        product.setImage(genericProductDTO.getImage());
        product.setTitle(genericProductDTO.getTitle());
        product.setPrice(new Price(genericProductDTO.getPrice(), "Rupee"));
        Product savedProduct =productRepository.save(product);
        return DTOMapper.productToGenericProductDTO(savedProduct);
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException {
        Optional<Product>optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        Product product = optionalProduct.get();
        product.setPrice(new Price(genericProductDTO.getPrice(), "Rupee"));
        product.setTitle(genericProductDTO.getTitle());
        product.setImage(genericProductDTO.getImage());
        product.setDescription(genericProductDTO.getDescription());
        product.setCategory(new Category(genericProductDTO.getCategory()));
        Product savedProduct = productRepository.save(product);
        return DTOMapper.productToGenericProductDTO(savedProduct);
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(UUID.fromString(id));
        if(optionalProduct.isEmpty()){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }else {
            productRepository.deleteById(UUID.fromString(id));
        }
        return DTOMapper.productToGenericProductDTO(optionalProduct.get());
    }
}
