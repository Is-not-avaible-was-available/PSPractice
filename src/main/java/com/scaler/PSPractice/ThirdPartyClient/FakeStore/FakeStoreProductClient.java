package com.scaler.PSPractice.ThirdPartyClient.FakeStore;

import com.scaler.PSPractice.ThirdPartyClient.FakeStore.dtos.FakeStoreProductDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Value("${fakestore.api.baseurl}")
    private String fakeStoreApiBaseUrl ;
    @Value("${fakestore.api.product}")
    private String fakeStoreProductPath;

    private final String productPath = "/products";
    private String productUrl=  fakeStoreApiBaseUrl + fakeStoreProductPath +"/{id}";
    private String allProductUrl = fakeStoreApiBaseUrl + productPath;
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    public FakeStoreProductClient (RestTemplateBuilder  restTemplateBuilder,
                                   @Value("${fakestore.api.baseurl}") String fakeStoreApiBaseUrl,
                                   @Value("${fakestore.api.product}")String fakeStoreProductPath){
        this.restTemplateBuilder = restTemplateBuilder;
        this.productUrl = fakeStoreApiBaseUrl+productPath+"/{id}";
        this.allProductUrl = fakeStoreApiBaseUrl + fakeStoreProductPath;
    }

    public FakeStoreProductDTO findProductById(Long id) throws NotFoundException {
        System.out.println(fakeStoreApiBaseUrl);
        System.out.println(productUrl);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductServiceResponseEntity =
                restTemplate.getForEntity(productUrl, FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO= fakeStoreProductServiceResponseEntity.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Element with id " +id+", is not found!");
        }
        return fakeStoreProductDTO;
    }


    public List<FakeStoreProductDTO> findAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO[]> fakeStoreResponseEntity =
                restTemplate.getForEntity(allProductUrl, FakeStoreProductDTO[].class);

        FakeStoreProductDTO[] fakeStoreProductDTOS = fakeStoreResponseEntity.getBody();

        return Arrays.asList(fakeStoreProductDTOS);
    }


    public FakeStoreProductDTO deleteProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponse =
                restTemplate.exchange(productUrl, HttpMethod.DELETE, null, FakeStoreProductDTO.class, id);
        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponse.getBody();
        if(fakeStoreProductDTO==null){
            throw new NotFoundException("Element with id: " + id+ ", is not available!");
        }

        return fakeStoreProductDTO;
    }
    public FakeStoreProductDTO createProduct(GenericProductDTO newProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.postForEntity(allProductUrl, newProductDTO, FakeStoreProductDTO.class);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return fakeStoreProductDTO;
    }


    public FakeStoreProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDTO> fakeStoreProductDTOResponseEntity =
                restTemplate.exchange(productUrl,HttpMethod.PUT, new HttpEntity<>(genericProductDTO)
                        , FakeStoreProductDTO.class, id);

        FakeStoreProductDTO fakeStoreProductDTO = fakeStoreProductDTOResponseEntity.getBody();

        return fakeStoreProductDTO;
    }

}
