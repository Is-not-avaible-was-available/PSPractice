package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.ThirdParty.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.Exception.NotFoundException;
import com.scaler.PSPractice.Mapper.DTOMapper;
import com.scaler.PSPractice.ThirdParty.FakeStore.FakeStoreProductClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private FakeStoreProductClient fakeStoreProductClient;
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }
    @Override
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO) {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.createProduct(genericProductDTO);
        return DTOMapper.fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO deleteProductById(String id) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.deleteProductById(id);
        return DTOMapper.fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.updateProductById(id, genericProductDTO);
        return DTOMapper.fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO findProductById(String id) throws NotFoundException {
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.getProductById(id);
        return DTOMapper.fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public List<GenericProductDTO> findAllProducts() throws NotFoundException {
        List<FakeStoreProductDTO> fakeStoreProductDTOs = fakeStoreProductClient.getAllProducts();
        List<GenericProductDTO> genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO:fakeStoreProductDTOs){
            genericProductDTOS.add(DTOMapper.fakeToGenericProductDTO(fakeStoreProductDTO));
        }
        return genericProductDTOS;
    }
}
