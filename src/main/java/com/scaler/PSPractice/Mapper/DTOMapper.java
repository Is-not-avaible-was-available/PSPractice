package com.scaler.PSPractice.Mapper;

import com.scaler.PSPractice.ThirdParty.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.models.Category;
import com.scaler.PSPractice.models.Price;
import com.scaler.PSPractice.models.Product;

public class DTOMapper {
    public static GenericProductDTO fakeToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        return genericProductDTO;
    }

    public static GenericProductDTO productToGenericProductDTO(Product product){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(product.getUuid().toString());
        genericProductDTO.setTitle(product.getTitle());
        genericProductDTO.setPrice(genericProductDTO.getPrice());
        genericProductDTO.setCategory(product.getCategory().getName());
        genericProductDTO.setImage(product.getImage());
        genericProductDTO.setDescription(product.getDescription());
        return genericProductDTO;
    }
}
