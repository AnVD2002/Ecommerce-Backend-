package com.example.Ecommerce.DTO.Response;
import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterResponse {
    private String username;

    private String Password;

    private String Email;

    private String PhoneNumber;

    private boolean status;

    private String code;

    private String message;
}
