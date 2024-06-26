package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.dto.ListProductsResponseDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("KarthikFakeStoreService")
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId) throws DBTimeoutException, DBNotFoundException, ProductNotFoundException {
        Product product = productService.getSingleProduct(productId);

        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setProduct(product);
        productResponseDTO.setResponseMessage("success");

        ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponseDTO>  getAllProducts(){
        List<Product> products = productService.getAllProducts();
        ListProductsResponseDTO responseDTO = new ListProductsResponseDTO();
        responseDTO.setProductList(products);
        responseDTO.setResponseMessage("SUCCESS");
        ResponseEntity<ListProductsResponseDTO> responseEntity = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/search")
    // localhost:9000/products?text="hello"
    public List<Product> searchProducts(@RequestParam("text") String queryText){
        List<Product> products = productService.searchProducts(queryText);
        return products;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody FakeStoreRequestDTO fakeStoreRequestDTO){
        Product savedProduct = productService.createProduct(fakeStoreRequestDTO);
        return savedProduct;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException errorObject){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setErrorMessage("from controller " + errorObject.getMessage());
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;
    }
}
