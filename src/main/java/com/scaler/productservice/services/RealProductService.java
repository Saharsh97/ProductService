package com.scaler.productservice.services;

import com.scaler.productservice.dto.FakeStoreRequestDTO;
import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("RealDBProductService")
public class RealProductService implements ProductService{


    ProductRepository productRepository;
    CategoryRepository categoryRepository;

    @Autowired
    public RealProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(String productId) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
//        return productRepository.getAllProducts();
        return null;
    }

    @Override
    public List<Product> getProductsByCategoryName(String categoryName) {
        List<Product> response =  productRepository.getProductByCategoryName(categoryName);
        return response;
    }

    @Override
    public List<Product> searchProducts(String searchText) {
        List<Product> productsFromDB = productRepository.getProductByName("");

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
        Optional<Category> optionalCategory = categoryRepository.getCategoryByName(product.getCategory().getName());
        // if category table has no entry for laptop
        if(optionalCategory.isEmpty()){
            Category category = new Category();
            category.setName(product.getCategory().getName());
            Category savedCategoryObject = categoryRepository.save(category);
            // this has an id = 1.
            product.setCategory(savedCategoryObject);
        } else {
            Category alreadyPresentCategory = optionalCategory.get();
            product.setCategory(alreadyPresentCategory);
        }

        return productRepository.save(product);
    }

    @Override
    public Product createProduct(FakeStoreRequestDTO fakeStoreRequestDTO) {
        return null;
    }
}
