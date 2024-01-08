package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO findProductById(Long id) throws NotFoundException;
    public List<GenericProductDTO> findAllProducts();
    public GenericProductDTO deleteProductById(Long id) throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);

    public GenericProductDTO updateProductById(Long id, GenericProductDTO genericProductDTO);
}
