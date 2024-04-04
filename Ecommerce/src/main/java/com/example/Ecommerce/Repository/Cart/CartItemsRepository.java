package com.example.Ecommerce.Repository.Cart;

import com.example.Ecommerce.Entity.Cart.CartItem;
import com.example.Ecommerce.Entity.Products.Categories;
import com.example.Ecommerce.Entity.Products.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItem,Integer> {
    @Query(value = "select * from cartitem where productid= :productid and cartid  =:cartid ", nativeQuery = true)
    public Optional<CartItem> GetByCartAndProduct(@Param("productid") int productId, @Param("cartid") int cartId);
}
