package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.FakeStoreDTO;
import com.scaler.productservice.dto.ListProductsResponseDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

//    @Autowired
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId){
        ResponseEntity<ProductResponseDTO> responseEntity;
        try {
            Product product = productService.getSingleProduct(productId);

            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(product);
            productResponseDTO.setResponseMessage("success");

            responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
        } catch (Exception e){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(null);
            productResponseDTO.setResponseMessage("error occured: " + e.getMessage());

            responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsResponseDTO> getAllProducts(){
        ResponseEntity<ListProductsResponseDTO> responseEntity;
        try {
            List<Product> products = productService.getAllProducts();

            ListProductsResponseDTO listProductsResponseDTO = new ListProductsResponseDTO();
            listProductsResponseDTO.setProducts(products);
            listProductsResponseDTO.setResponseMessage("success");

            responseEntity = new ResponseEntity<>(listProductsResponseDTO, HttpStatus.OK);
        } catch (Exception e){
            ListProductsResponseDTO listProductsResponseDTO = new ListProductsResponseDTO();
            listProductsResponseDTO.setProducts(null);
            listProductsResponseDTO.setResponseMessage("error in /products");

            responseEntity = new ResponseEntity<>(listProductsResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/search")
    // localhost:9000/products?text="hello"
    public List<Product> searchProducts(@RequestParam("text") String queryText){
        List<Product> products = productService.searchProducts(queryText);
        return products;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody FakeStoreDTO fakeStoreDTO){
        Product savedProduct = productService.createFakeStoreProduct(fakeStoreDTO);
        return savedProduct;
    }
}
