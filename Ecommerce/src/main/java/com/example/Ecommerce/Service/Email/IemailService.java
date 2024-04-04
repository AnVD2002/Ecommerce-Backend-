package com.example.Ecommerce.Service.Email;

import com.example.Ecommerce.DTO.Request.ConfirmRequest;
import com.example.Ecommerce.DTO.Request.RegisterRequest;
import com.example.Ecommerce.DTO.Response.MessageResponse;
import com.example.Ecommerce.DTO.Response.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
public interface IemailService {
    public ResponseEntity<?> XacNhanUser(ConfirmRequest confirmRequest);
    public RegisterResponse Register(RegisterRequest registerRequest);
    public void sendConfirmationEmail(String recipientEmail, String confirmationCode );

}
