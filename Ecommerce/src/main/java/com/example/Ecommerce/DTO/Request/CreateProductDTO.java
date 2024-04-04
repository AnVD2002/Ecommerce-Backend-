package com.example.Ecommerce.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductDTO {
    String ProductName;
    String ProductImage;
    String Description;
    int ProductTypeID;
    int ProductCategoryID;
}
