package com.example.Ecommerce.DTO.Request;

import com.example.Ecommerce.DTO.Response.RegisterResponse;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConfirmRequest {
    RegisterResponse registerResponse;
    String code;
}
