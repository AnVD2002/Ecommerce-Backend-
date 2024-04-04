package com.example.Ecommerce.DTO.Request;
import lombok.*;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductDTO {
    private String name;
    private String description;
    private int categoryID;
    private String productImage;
    private List<ProductVariantDTO> productVariants;
}
