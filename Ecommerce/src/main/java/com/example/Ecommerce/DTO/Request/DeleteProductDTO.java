package com.example.Ecommerce.DTO.Request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteProductDTO {
    private int ProductID;
    private int SizeID;
    private int ColorID;
}
