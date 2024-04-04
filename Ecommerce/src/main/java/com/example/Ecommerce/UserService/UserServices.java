package com.example.Ecommerce.UserService;
import com.example.Ecommerce.Repository.UserRepo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class UserServices implements IUserServices {
    @Autowired
    UserRepository userRepository;
    public ResponseEntity<?> GetAllUser(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }
}
