package com.scaler.productservice.services;

import com.scaler.productservice.dto.ErrorResponseDTO;
import com.scaler.productservice.dto.FakeStorePOSTResponseDTO;
import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.dto.FakeStoreResponseDTO;
import com.scaler.productservice.exceptions.DBNotFoundException;
import com.scaler.productservice.exceptions.DBTimeoutException;
import com.scaler.productservice.exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("KarthikFakeStoreService")
public class FakeStoreProductService implements ProductService{

    // It has to hit the APIs of FakeStore server
    // we use RestTemplate to call 3rd party APIs
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Product getSingleProduct(String productId) throws ProductNotFoundException, DBNotFoundException, DBTimeoutException {
        FakeStoreResponseDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreResponseDTO.class
        );
        if(response == null){
            throw new ProductNotFoundException("product with id " + productId + " not found");
        }
        connectToDB();
        executeSQLQuery();
        // 1. you hit the API, you get back an object.
        // 2. You want to Structure the Object, into a particular format -> FakeStoreResponse.class
        // 3. convert the class structure, to its corresponding object -> response
        Product product = response.toProduct();
        return product;
    }

    private void connectToDB() throws DBNotFoundException {
        // connect to DB
        throw new DBNotFoundException("db not found");
    }

    private void executeSQLQuery() throws DBTimeoutException {
        throw new DBTimeoutException("db got timedout trying to execute SQL query");
    }

    @Override
    public Page<Product> getAllProducts(Integer pageSize, Integer pageNumber, String sortField, String sortOrder) {
//        FakeStoreResponseDTO[] responseArray = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreResponseDTO[].class
//        );
//        List<Product> productsList = new ArrayList<>();
//        for(FakeStoreResponseDTO response: responseArray){
//            Product product = response.toProduct();
//            productsList.add(product);
//        }
//        return productsList;
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        return List.of();
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
        FakeStorePOSTResponseDTO savedProductResponse = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                fakeStoreRequestDTO,
                FakeStorePOSTResponseDTO.class
        );

        Product product = savedProductResponse.toProduct();
        return product;
    }
}
