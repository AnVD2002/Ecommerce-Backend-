package com.example.Ecommerce.DTO.Request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private String tenTK;
    private String mail;
    private String password;
}
