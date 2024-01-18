package com.scaler.PSPractice.Services;

import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.Exception.NotFoundException;

import java.util.List;

public interface ProductService {
    public GenericProductDTO findProductById(String id) throws NotFoundException;
    public List<GenericProductDTO> findAllProducts() throws NotFoundException;
    public GenericProductDTO createProduct(GenericProductDTO genericProductDTO);
    public GenericProductDTO updateProductById(String id, GenericProductDTO genericProductDTO) throws NotFoundException;
    public GenericProductDTO deleteProductById(String id) throws NotFoundException;
}
