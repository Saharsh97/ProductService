package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.FakeStoreDTO;
import com.scaler.productservice.dto.ListProductsDTO;
import com.scaler.productservice.dto.ProductDTO;
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
    public ResponseEntity<ProductDTO> getSingleProduct(@PathVariable("id") String productId){
        ResponseEntity<ProductDTO> responseEntity;
        try {
            Product product = productService.getSingleProduct(productId);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct(product);
            productDTO.setResponseMessage("success");

            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (Exception e){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProduct(null);
            productDTO.setResponseMessage("error occured: " + e.getMessage());

            responseEntity = new ResponseEntity<>(productDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductsDTO> getAllProducts(){
        ResponseEntity<ListProductsDTO> responseEntity;
        try {
            List<Product> products = productService.getAllProducts();

            ListProductsDTO listProductsDTO = new ListProductsDTO();
            listProductsDTO.setProducts(products);
            listProductsDTO.setResponseMessage("success");

            responseEntity = new ResponseEntity<>(listProductsDTO, HttpStatus.OK);
        } catch (Exception e){
            ListProductsDTO listProductsDTO = new ListProductsDTO();
            listProductsDTO.setProducts(null);
            listProductsDTO.setResponseMessage("error in /products");

            responseEntity = new ResponseEntity<>(listProductsDTO, HttpStatus.INTERNAL_SERVER_ERROR);
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
