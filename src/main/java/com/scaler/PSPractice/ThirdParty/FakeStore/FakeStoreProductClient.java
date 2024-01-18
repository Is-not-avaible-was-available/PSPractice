package com.scaler.PSPractice.ThirdParty.FakeStore;

import com.scaler.PSPractice.ThirdParty.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.Exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component

public class FakeStoreProductClient {
    private String singleProductUrl;
    private String allProductUrl;
    private final String productPath = "/products";
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}")String fakeStoreBaseUrl,
                                  @Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.singleProductUrl = fakeStoreBaseUrl + productPath + "/{id}";
        this.allProductUrl = fakeStoreBaseUrl + fakeStoreProductPath;
        this.restTemplateBuilder = restTemplateBuilder;
    }


    public FakeStoreProductDTO getProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntity =
        restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }

    public List<FakeStoreProductDTO> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreProductDTOResponseEntity =
        restTemplate.getForEntity(allProductUrl, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTOS==null){
            throw new NotFoundException("products not found!");
        }
        return Arrays.asList(fakeStoreProductDTOS);
    }
    public FakeStoreProductDTO createProduct(GenericProductDTO  genericProductDTO){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.postForEntity(allProductUrl, genericProductDTO, FakeStoreProductDTO.class);
        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public FakeStoreProductDTO updateProductById(String id, GenericProductDTO updatedProduct) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl, HttpMethod.PUT,
                new HttpEntity<>(updatedProduct), FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO == null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }
    public FakeStoreProductDTO deleteProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO>fakeStoreProductDTOResponseEntity=
        restTemplate.exchange(singleProductUrl, HttpMethod.DELETE, null,
                FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }
}
