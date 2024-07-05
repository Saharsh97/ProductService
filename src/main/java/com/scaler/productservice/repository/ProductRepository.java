package com.scaler.productservice.repository;

import com.mysql.cj.log.Log;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long>{

    // declared queries
    Product save(Product product);

    Integer countProductByPriceLessThan(Double value);

    List<Product> getProductByName(String name);
    // select count(*) from products p where p.price < value;

    List<Product> getProductByNameLikeAndDescriptionLikeOrderByPriceDesc(String nameText, String descText);
    List<Product> getProductByCategoryName(String categoryName);
    // select * from products p JOIN category c on p.category_id = c.id and c.name = categoryName

    void deleteProductById(Long id);    // hard delete -> permanent deletion of the row.


    @Query("select p from Product p")
    List<Product> sabKuchDedo();

    @Query("select p from Product p where p.category.name = :categoryName")
    List<Product> allProductsGivenCatNameUsingHQL(@Param("categoryName") String catName);

    @Query(value = "select p.* from product p LEFT JOIN category c ON p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
    List<Product> properSQLQuery(@Param("categoryName") String catName);

    // using SQL
    @Query(value = "select p.id, p.name from product p LEFT JOIN category c ON p.category_id = c.id where c.name = :categoryName", nativeQuery = true)
    List<ProductProjection> sqlQueryUsingProjection(@Param("categoryName") String catName);

    // using HQL.
    // alias is necessary here.
    @Query(value = "select p.id as id, p.name as name from Product p where p.category.name = :categoryName")
    List<ProductProjection> hqlQueryUsingProjection(@Param("categoryName") String catName);
}
