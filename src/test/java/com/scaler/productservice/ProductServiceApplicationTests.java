package com.scaler.productservice;

import com.scaler.productservice.models.Product;
import com.scaler.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testAllProductsUsingHQL(){
        List<Product> productList = productRepository.sabKuchDedo();
        for(Product product: productList){
            System.out.println(product.getName());
        }
    }

    @Test
    void testAllProductsUsingHQLAndCatName(){
        List<Product> productList = productRepository.allProductsGivenCatNameUsingHQL("laptop");
        for(Product product: productList){
            System.out.println(product.getName());
        }
    }

    @Test
    void sqlChalaLo(){
        List<Product> productList = productRepository.properSQLQuery("laptop");
        for(Product product: productList){
            System.out.println(product.getName());
        }
    }

}
