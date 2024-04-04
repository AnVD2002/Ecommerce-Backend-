package com.example.Ecommerce.Repository.Order;

import com.example.Ecommerce.Entity.Cart.CartItem;
import com.example.Ecommerce.Entity.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer> {
}
