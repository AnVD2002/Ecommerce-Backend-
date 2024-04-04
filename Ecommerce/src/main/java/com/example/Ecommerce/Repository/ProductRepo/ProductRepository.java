package com.example.Ecommerce.Repository.ProductRepo;

import com.example.Ecommerce.Entity.Products.ProductVarients;
import com.example.Ecommerce.Entity.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products,Integer> {
    @Query(value = "select * from products where name= :name ", nativeQuery = true)
    public Optional<Products> FindProductByName(@Param("name") String name);
    @Query(value = "select * from products where categoryid = 2 and outstandingproduct = true ", nativeQuery = true)
    public List<Products> GetProductOutstandingProductWomen();
    @Query(value = "select * from products where categoryid = 1 and outstandingproduct = true ", nativeQuery = true)
    public List<Products> GetProductOutstandingProductMen();
    @Query(value = "select * from products where categoryid = 3 and outstandingproduct = true ", nativeQuery = true)
    public List<Products> GetProductOutstandingProductKid();
    @Query(value = "select * from products where outstandingproduct = true ", nativeQuery = true)
    public List<Products> GetProductOutstandingProduct();



}
