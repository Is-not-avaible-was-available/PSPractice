package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO getProductById(String id) throws NotFoundException;

    public List<GenericProductDTO> getAllProducts() throws NotFoundException;

    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    public GenericProductDTO deleteProductById(String id) throws NotFoundException;

    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException;
}
