package com.example.Ecommerce.Repository.ProductRepo;


import com.example.Ecommerce.Entity.Products.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories,Integer> {
}
