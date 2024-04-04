package com.example.Ecommerce.Repository.ProductRepo;

import com.example.Ecommerce.Entity.Products.ProductVarients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVarientRepository extends JpaRepository<ProductVarients,Integer> {
    @Query(value = "select * from productvarients where colorid = :colorID and sizeid = :sizeID and productid = :productID", nativeQuery = true)
    public Optional<ProductVarients> findByProductIdAndColorIdAndSizeId(@Param("colorID") int colorID, @Param("sizeID") int sizeID , @Param("productID") int productID );
}


