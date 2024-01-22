package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.ThirdParty.FakeStoreProductClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDTO fakeStoreDtoToGenericDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        return genericProductDTO;
    }

    @Override
    public GenericProductDTO getProductById(String id) throws NotFoundException {
        return fakeStoreDtoToGenericDTO(fakeStoreProductClient.getProductById(id));
    }

    @Override
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        List<FakeStoreProductDTO> fakeStoreProductDTOS = fakeStoreProductClient.getAllProducts();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();

        for(FakeStoreProductDTO fakeStoreProductDTO:fakeStoreProductDTOS){
            genericProductDTOS.add(fakeStoreDtoToGenericDTO(fakeStoreProductDTO));
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO)  {
        return fakeStoreDtoToGenericDTO(fakeStoreProductClient.createProduct(genericProductDTO));
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        return fakeStoreDtoToGenericDTO(fakeStoreProductClient.deleteProductById(id));
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) {
        return fakeStoreDtoToGenericDTO(fakeStoreProductClient.updateProductById(genericProductDTO,
                id));
    }
}
