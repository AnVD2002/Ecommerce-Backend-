package com.example.Ecommerce.Repository.Cart;

import com.example.Ecommerce.Entity.Cart.Cart;
import com.example.Ecommerce.Entity.Cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
