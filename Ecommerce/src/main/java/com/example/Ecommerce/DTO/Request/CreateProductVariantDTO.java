package com.example.Ecommerce.DTO.Request;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductVariantDTO {
    private int ProductID;
    private int SizeID;
    private int ColorID;
    private double OldPrice;
    private double NewPrice;
    private int Stock;
}
