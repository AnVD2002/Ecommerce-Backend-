package com.example.Ecommerce.DTO.Response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class jwtAuthenticationResponse {
    private String token;
    private String refreshToken;
}
