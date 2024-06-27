package com.scaler.productservice.controllers;


import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.dto.ProductResponseDTO;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.FakeStoreProductService;
import com.scaler.productservice.services.ProductService;
import com.scaler.productservice.services.RealProductService;
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
    ProductService productService;

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponseDTO> getSingleProduct(@PathVariable("id") String productId) {
        try {
            Product product = productService.getSingleProduct(productId);

            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(product);
            productResponseDTO.setResponseMessage("success");

            ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.OK);
            return responseEntity;
        }
        catch (ProductNotFoundException pnfe){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProduct(null);
            productResponseDTO.setResponseMessage(pnfe.getMessage() + "exception type 1");

            ResponseEntity<ProductResponseDTO> responseEntity = new ResponseEntity<>(productResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }
//        catch (DBNotFoundException dbnfe) {
//            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//            productResponseDTO.setProduct(null);
//            productResponseDTO.setResponseMessage(dbnfe.getMessage()  + "exception type 2");
//            return productResponseDTO;
//        }
//        catch (DBTimeoutException dbte) {
//            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
//            productResponseDTO.setProduct(null);
//            productResponseDTO.setResponseMessage(dbte.getMessage()  + "exception type 1");
//            return productResponseDTO;
//        }
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
