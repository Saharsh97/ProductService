package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(String productId);

    List<Product> getAllProducts();

    List<Product> searchProducts(String searchText);

    // to create object in your own DB
    Product createProduct(Product product);

    // to create objects in FakeStore DB
    // not the right way to exactly.
    Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO);
}
