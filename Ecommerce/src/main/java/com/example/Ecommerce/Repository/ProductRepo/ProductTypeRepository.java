package com.example.Ecommerce.Repository.ProductRepo;

import com.example.Ecommerce.Entity.Products.ProductType;
import com.example.Ecommerce.Entity.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType,Integer> {
}
