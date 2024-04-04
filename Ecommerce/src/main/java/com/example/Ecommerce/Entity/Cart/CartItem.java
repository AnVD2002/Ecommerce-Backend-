package com.example.Ecommerce.Entity.Cart;

import com.example.Ecommerce.Entity.Products.Products;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Table(name = "cartitem")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class CartItem {
    @Id
    @Column(name = "cartitemid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartItemID;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "status")
    private int status;
    @Column(name = "createat")
    private LocalDate createAt;
    @Column(name = "updateat")
    private LocalDate updateAt;
    @Column(name = "productid", insertable = false, updatable = false)
    private int productID;
    @Column (name = "cartid", insertable = false, updatable = false)
    private int CartID;
    @ManyToOne
    @JsonBackReference("products-cartitem")
    @JoinColumn(name = "productid")
    private Products product;
    @ManyToOne
    @JsonBackReference("cart-cartitems")
    @JoinColumn(name = "cartid")
    private Cart cart;


}
