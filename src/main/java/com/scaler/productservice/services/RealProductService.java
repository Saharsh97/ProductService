package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("UjwalProductService")
public class RealProductService implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getSingleProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        List<Product> productsFromDB = productRepository.getAllProducts();

        // core logic, filter products, whether their name contains the searchText or not
        List<Product> result = new ArrayList<>();
        for(Product product : productsFromDB){
            if(product.getName().contains(searchText)){
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }
}
