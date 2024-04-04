package com.example.Ecommerce.Entity.Cart;

import com.example.Ecommerce.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Cart {
    @Id
    @Column(name = "cartid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int CartID;
    @Column(name = "createat")
    private LocalDate CreateAt;
    @Column(name = "updateat")
    private LocalDate UpdateAt;
    @Column(name = "userid", insertable = false, updatable = false)
    private int UserID;
    @Column(name = "cartid" , insertable = false, updatable = false)
    private int CartItemID;
    @ManyToOne
    @JsonBackReference("user-carts")
    @JoinColumn(name = "userid")
    private User user;
    @OneToMany(mappedBy = "cart",fetch = FetchType.LAZY)
    @JsonManagedReference("cart-cartitems")
    private List<CartItem> cartItem;


}
