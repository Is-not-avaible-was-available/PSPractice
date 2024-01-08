package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.ExceptionDTO;
import com.scaler.PSPractice.DTOs.FakeStoreProductDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private String productUrl=  "https://fakestoreapi.com/products/{id}";
    private String allProductUrl = "https://fakestoreapi.com/products";
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductService(RestTemplateBuilder  restTemplateBuilder){
        this.restTemplateBuilder = restTemplateBuilder;
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
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductServiceResponseEntity =
        restTemplate.getForEntity(productUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreProductServiceResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Element with id " +id+", is not found!");
        }
        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public List<GenericProductDTO> findAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreResponseEntity =
        restTemplate.getForEntity(allProductUrl, FakeStoreProductDTO[].class);

        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreResponseEntity.getBody();
        List<GenericProductDTO>genericProductDTOS = new ArrayList<>();
        for(FakeStoreProductDTO fakeStoreProductDTO: fakeStoreProductDTOS){

            GenericProductDTO genericProductDTO = fakeToGenericProductDTO(fakeStoreProductDTO);
            genericProductDTOS.add(genericProductDTO);
        }
        return genericProductDTOS;
    }

    @Override
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponse =
        restTemplate.exchange(productUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponse.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Element with id: " + id+ ", is not available!");
        }

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO createProduct(GenericProductDTO newProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.postForEntity(allProductUrl, newProductDTO, FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }

    @Override
    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.exchange(productUrl,HttpMethod.PUT, new HttpEntity<>(genericProductDTO)
                , FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return fakeToGenericProductDTO(fakeStoreProductDTO);
    }


}
