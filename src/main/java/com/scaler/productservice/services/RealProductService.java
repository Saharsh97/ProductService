package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("UjwalProductService")
public class RealProductService implements ProductService{
    @Override
    public Product getSingleProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>();
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }
}
