package com.scaler.productservice;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductProjection;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceApplicationTests(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void saveProduct(){
        Product product = new Product();
        product.setName("test1");
        product.setPrice(10000.0);
        Category category = new Category();
        category.setName("cat");
        product.setCategory(category);
        productRepository.save(product);
    }

//    @Test
//    @Transactional
//    void deleteProduct(){
//        productRepository.deleteProductById(12L);
//    }
//    @Commit

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

    @Test
    void sqlUsingProjection(){
        List<ProductProjection> productProjections = productRepository.sqlQueryUsingProjection("laptop");
        for(ProductProjection projection: productProjections){
            System.out.println(projection.getId() + " : " + projection.getName());
        }
    }

    @Test
    void hqlUsingProjection(){
        List<ProductProjection> productProjections = productRepository.hqlQueryUsingProjection("laptop");
        for(ProductProjection projection: productProjections){
            System.out.println(projection.getId() + " : " + projection.getName());
        }
    }


    @Test
    void fetchProductsEagerly(){
        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
        if(categoryOptional.isEmpty()){
            return;
        }
        Category category = categoryOptional.get();
        System.out.println(category.getName());
    }


    @Test
    void fetchProductsLazilyP1(){
        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
        if(categoryOptional.isEmpty()){
            return;
        }
        Category category = categoryOptional.get();
        System.out.println(category.getName());
    }

    @Test
    void fetchProductsLazilyP2(){
        Optional<Category> categoryOptional = categoryRepository.getCategoryByName("laptop");
        if(categoryOptional.isEmpty()){
            return;
        }
        Category category = categoryOptional.get();
        System.out.println(category.getName());

        List<Product> products = category.getProducts();
        System.out.println(products.size());
        for(Product product: products){
            System.out.println(product.getName());
        }
    }

    @Test
    void testNPlusOneProblem(){
        List<Category> categories = categoryRepository.getCategoriesByNameContaining("laptop");

        for(Category category: categories) {
            System.out.println(category.getName());

            List<Product> products = category.getProducts();
            System.out.println(products.size());
            for (Product product : products) {
                System.out.println(product.getName());
            }
        }
    }

}
