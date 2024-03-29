package com.scaler.PSPractice.Mappers;

import com.scaler.PSPractice.DTOs.CategoryDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Models.Category;
import com.scaler.PSPractice.Models.Product;
import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;

public class DTOMappers {
    public static CategoryDTO categoryToCategoryResponseDTO(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getUuid().toString());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    public static GenericProductDTO productToGenericProductDTO(Product product){
        if(product==null){
            return null;
        }
        System.out.println("hi from mapper class");
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(product.getUuid().toString());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setDescription(product.getDescription());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setPrice(product.getPrice().getPrice());
        genericProductDTO.setCategory(product.getCategory().getName());
        return genericProductDTO;
    }

    public static GenericProductDTO fakeStoreDtoToGenericDTO(FakeStoreProductDTO fakeStoreProductDTO){
        if(fakeStoreProductDTO==null){
            return null;
        }
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        return genericProductDTO;
    }
}
