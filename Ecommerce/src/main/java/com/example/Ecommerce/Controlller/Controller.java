package com.example.Ecommerce.Controlller;

import com.example.Ecommerce.DTO.Request.*;
import com.example.Ecommerce.DTO.Response.MessageResponse;
import com.example.Ecommerce.DTO.Response.RegisterResponse;
import com.example.Ecommerce.Service.Email.emailService;
import com.example.Ecommerce.Service.Jwt.jwtService;
import com.example.Ecommerce.Service.Product.ProductService;
import com.example.Ecommerce.UserService.UserServices;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/account/")
@RestController
public class Controller {
    @Autowired
    public emailService emailService;
    @Autowired
    public jwtService userService;
    @Autowired
    public ProductService productService;
    @Autowired
    public UserServices userServices;
    @CrossOrigin
    @PostMapping(path = "CreateAccount",produces = MediaType.APPLICATION_JSON_VALUE)
    public RegisterResponse CreateAccount(@RequestBody RegisterRequest registerRequest ){
        return emailService.Register(registerRequest);
    }
    @CrossOrigin
    @PostMapping(path = "ConfirmAccount",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ConFirmAccount(@RequestBody ConfirmRequest confirmRequest){
        return emailService.XacNhanUser(confirmRequest);
    }
    @CrossOrigin
    @PostMapping(path = "Login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> Login(@RequestBody LoginRequest loginRequest ){
        return ResponseEntity.ok(userService.Login(loginRequest));
    }
    @CrossOrigin
    @DeleteMapping(path = "DeleteProducts",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> DeleteProduct(@RequestBody DeleteProductDTO deleteProductDTO){
        return ResponseEntity.ok(productService.DeleteProduct(deleteProductDTO));
    }
    @CrossOrigin
    @PostMapping(path = "CreateProduct",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> CreateProduct(@RequestBody CreateProductDTO createProductDTO){
        return ResponseEntity.ok(productService.CreateProduct(createProductDTO));
    }
    @PutMapping(path = "CreateProductVariant",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> CreateProductVariant(@RequestBody CreateProductVariantDTO createProductVariantDTO){
        return ResponseEntity.ok(productService.CreateProductVariant(createProductVariantDTO));
    }
    @CrossOrigin
    @GetMapping(path = "AllColors", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetAllColors(){
        return productService.GetAllColor();
    }
    @CrossOrigin
    @GetMapping(path = "AllSize", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetAllSize(){
        return productService.GetAllSize();
    }
    @CrossOrigin
    @GetMapping(path = "AllProduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetAllProduct(){
        return productService.GetAllProduct();
    }
    @CrossOrigin
    @GetMapping(path = "AllUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetAllUser(){
        return userServices.GetAllUser();
    }
    @CrossOrigin
    @GetMapping(path = "MensProductOutStanding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetMensProductOutStanding(){
        return productService.GetMenProductOutStanding();
    }
    @CrossOrigin
    @GetMapping(path = "WomenProductOutStanding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetWomenProductOutStanding(){return productService.GetWomenProductOutStanding();
    }
    @CrossOrigin
    @GetMapping(path = "KidProductOutStanding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetKidProductOutStanding(){
        return productService.GetKidProductOutStanding();
    }
    @CrossOrigin
    @GetMapping(path = "ProductOutStanding", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetProductOutStanding(){
        return productService.GetProductOutStanding();
    }

}
