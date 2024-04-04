package com.example.Ecommerce.DTO.Request;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequest {
    private int idCart;

    private int idProduct;

    private int quantity;
}
