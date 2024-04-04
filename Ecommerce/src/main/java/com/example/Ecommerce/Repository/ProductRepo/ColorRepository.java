package com.example.Ecommerce.Repository.ProductRepo;

import com.example.Ecommerce.Entity.Products.Categories;
import com.example.Ecommerce.Entity.Products.Colors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ColorRepository extends JpaRepository<Colors,Integer> {
}
