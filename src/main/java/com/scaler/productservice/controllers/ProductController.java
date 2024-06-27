package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import com.scaler.productservice.services.RealProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") String productId){
        Product product = productService.getSingleProduct(productId);
        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return products;
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
}
