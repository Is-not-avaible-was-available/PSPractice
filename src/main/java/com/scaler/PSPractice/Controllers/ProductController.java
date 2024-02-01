package com.scaler.PSPractice.Controllers;

import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    @Autowired
    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public GenericProductDTO getProductById(@PathVariable("id") String id) throws NotFoundException {
        System.out.println("Calling from productController");
        return productService.getProductById(id);
    }
    @GetMapping
    public List<GenericProductDTO> getAllProducts() throws NotFoundException {
        return productService.getAllProducts();
    }

    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO) {
        return productService.createProduct(genericProductDTO);
    }

    @DeleteMapping("/{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") String id) throws NotFoundException {
        return productService.deleteProductById(id);
    }

    @PutMapping("/{id}")
    public GenericProductDTO updateProductById(@RequestBody GenericProductDTO genericProductDTO,
                                               @PathVariable("id") String id) throws NotFoundException {
        return productService.updateProductById(id, genericProductDTO);
    }
}
