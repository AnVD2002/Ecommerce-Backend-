package com.example.Ecommerce.Repository.ProductRepo;

import com.example.Ecommerce.Entity.Products.Sizes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Sizes,Integer> {
}
