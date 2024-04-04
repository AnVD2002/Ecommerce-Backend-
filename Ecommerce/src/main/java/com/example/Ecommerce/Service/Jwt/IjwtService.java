package com.example.Ecommerce.Service.Jwt;

import com.example.Ecommerce.DTO.Request.LoginRequest;
import com.example.Ecommerce.DTO.Response.TokenResponse;

public interface IjwtService {
    TokenResponse Login(LoginRequest loginRequest);

}
