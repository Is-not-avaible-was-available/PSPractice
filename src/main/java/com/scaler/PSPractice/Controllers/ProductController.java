package com.scaler.PSPractice.Controllers;

import com.scaler.PSPractice.DTOs.ExceptionDTO;
import com.scaler.PSPractice.DTOs.GenericProductDTO;
import com.scaler.PSPractice.Exceptions.NotFoundException;
import com.scaler.PSPractice.Services.ProductService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("{id}")
    public GenericProductDTO findProductById(@PathVariable("id") Long id) throws NotFoundException{
        return productService.findProductById(id);
    }
    @GetMapping
    public List<GenericProductDTO> findAllProducts(){

        return productService.findAllProducts();
    }
    @PutMapping("{id}")
    public GenericProductDTO updateProductById(@PathVariable("id") Long id
            ,@RequestBody GenericProductDTO genericProductDTO)  {

        return productService.updateProductById(id, genericProductDTO);
    }
    @DeleteMapping("{id}")
    public GenericProductDTO deleteProductById(@PathVariable("id") Long id) throws NotFoundException {
        return productService.deleteProductById(id);
    }
    @PostMapping
    public GenericProductDTO createProduct(@RequestBody GenericProductDTO genericProductDTO){
        return productService.createProduct(genericProductDTO);
    }

//This is specific to controller
//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
//        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND, notFoundException.getMessage())
//                , HttpStatus.NOT_FOUND);
//    }
}
