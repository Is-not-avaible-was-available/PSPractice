package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.ThirdPartyClient.FakeStore.FakeStoreProductClient;
import com.scaler.PSPractice.ThirdPartyClient.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Primary
@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{

    private FakeStoreProductClient fakeStoreProductClient;
    @Autowired
    public FakeStoreProductService(FakeStoreProductClient fakeStoreProductClient){
        this.fakeStoreProductClient = fakeStoreProductClient;
    }

    public GenericProductDTO fakeToGenericProductDTO(FakeStoreProductDTO fakeStoreProductDTO){
        GenericProductDTO genericProductDTO = new GenericProductDTO();
        genericProductDTO.setId(fakeStoreProductDTO.getId());
        genericProductDTO.setCategory(fakeStoreProductDTO.getCategory());
        genericProductDTO.setImage(fakeStoreProductDTO.getImage());
        genericProductDTO.setTitle(fakeStoreProductDTO.getTitle());
        genericProductDTO.setPrice(fakeStoreProductDTO.getPrice());
        genericProductDTO.setDescription(fakeStoreProductDTO.getDescription());
        return genericProductDTO;
    }
    @Override
    public GenericProductDTO findProductById(Long id) throws NotFoundException {

        return fakeToGenericProductDTO(fakeStoreProductClient.findProductById(id));
    }

    @Override
    public List<GenericProductDTO> findAllProducts() {


        List<FakeStoreProductDTO>fakeStoreProductDTOS = fakeStoreProductClient.findAllProducts();
        List<GenericProductDTO>genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){

            GenericProductDTO genericProductDTO = fakeToGenericProductDTO(fakeStoreProductDTO);
            genericProductDTOS.add(genericProductDTO);
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException {

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.deleteProductById(id);
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Element with id: " + id+ ", is not available!");
        }

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO) {


        return fakeToGenericProductDTO(fakeStoreProductClient.createProduct(newProductDTO));
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductClient.updateProductById(id,genericProductDTO);

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }


}
