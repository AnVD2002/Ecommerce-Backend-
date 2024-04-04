package com.example.Ecommerce.DTO.Request;

import com.example.Ecommerce.Entity.Products.Products;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateProductDTO {
    private int productID;
    private String name;
    private String description;
    private int categoryID;
    private String productImage;
    private List<ProductVariantDTO> productVariantDTOu;
}
