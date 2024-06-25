package com.scaler.productservice.services;

import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(String productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String searchText);

    Product createProduct(Product product);
}
