package com.scaler.PSPractice.ThirdParty;

import com.scaler.PSPractice.ThirdParty.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class FakeStoreProductClient {
    private String singleProductUrl;
    private String allProductsUrl;
    private RestTemplateBuilder restTemplateBuilder;
    private final String fakeStoreProductPath = "/products";

    public FakeStoreProductClient(RestTemplateBuilder restTemplateBuilder,
                                  @Value("${fakestore.api.baseurl}")String baseUrl,
                                  @Value("${fakestore.api.product}")String productPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.singleProductUrl = baseUrl+fakeStoreProductPath+"/{id}";
        this.allProductsUrl = baseUrl+productPath;
    }

    public FakeStoreProductDTO getProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.getForEntity(singleProductUrl, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){

            throw new NotFoundException("product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }

    public List<FakeStoreProductDTO> getAllProducts() throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreResponseEntity =
        restTemplate.getForEntity(allProductsUrl, FakeStoreProductDTO[].class);
        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreResponseEntity.getBody();
        if(fakeStoreProductDTOS==null){
            throw new NotFoundException("products not found!");
        }
        return Arrays.asList(fakeStoreProductDTOS);
    }

    public FakeStoreProductDTO createProduct(GenericProductDTO genericProductDTO)  {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.postForEntity(allProductsUrl, genericProductDTO, FakeStoreProductDTO.class);

        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public FakeStoreProductDTO updateProductById(GenericProductDTO genericProductDTO, String id){
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.exchange(singleProductUrl, HttpMethod.PUT, new HttpEntity<>(genericProductDTO),
                FakeStoreProductDTO.class, id);

        return fakeStoreProductDTOResponseEntity.getBody();
    }

    public FakeStoreProductDTO deleteProductById(String id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
        restTemplate.exchange(singleProductUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Product with id:"+id+", is not found!");
        }
        return fakeStoreProductDTO;
    }
}
