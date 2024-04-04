package com.example.Ecommerce.DTO.Request;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantDTO {
        private int colorID;
        private int sizeID;
        private double newPrice;
        private double oldPrice;
        private int stock;
}
