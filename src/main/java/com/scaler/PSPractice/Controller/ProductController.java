package com.scaler.PSPractice.Controller;

import com.scaler.PSPractice.DTos.GenericProductDTO;
import com.scaler.PSPractice.Exception.NotFoundException;
import com.scaler.PSPractice.Services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController{
    private ProductService productService;
    public ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        return productService.findAllProducts();
    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }
    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") String id,@RequestBody GenericProductDTO updatedProduct) throws NotFoundException {
        return productService.updateProductById(id, updatedProduct);
    }
}
