package com.example.Ecommerce.Service.Jwt;

import com.example.Ecommerce.Config.JwtTokenProvider;
import com.example.Ecommerce.DTO.Request.LoginRequest;
import com.example.Ecommerce.DTO.Response.TokenResponse;
import com.example.Ecommerce.Entity.User.*;
import com.example.Ecommerce.Enum.QuyenHanEnum;
import com.example.Ecommerce.Repository.UserRepo.DecentralizationRepository;
import com.example.Ecommerce.Repository.UserRepo.RegistrationFormRepository;
import com.example.Ecommerce.Repository.UserRepo.UserRepository;
import com.example.Ecommerce.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class jwtService implements IjwtService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RegistrationFormRepository registrationFormRepository;
    @Autowired
    public UserService userService;
    @Autowired
    public DecentralizationRepository decentralizationRepository;
    @Transactional
    @Override
    public TokenResponse Login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        Optional<User> findByUserName = userRepository.FindByName(loginRequest.getUsername());
        if(findByUserName.isPresent()){
                if (findByUserName.get().isConfirmed()) {
                    String jwtToken = jwtTokenProvider.generateToken(new CustomUserDetails(findByUserName.get()));
                    User user = UpdateToken(loginRequest.getUsername());
                    return TokenResponse.builder()
                            .token(user.getUpdatePasswordToken())
                            .accessToken(jwtToken)
                            .role(QuyenHanEnum.USER.toString())
                            .message("Login thanh cong")
                            .build();
                }
                return TokenResponse.builder().message("Chua tao tk").build();
            }
        return TokenResponse.builder().message("Sai ten tk hoac mk").build();
    }

    @Transactional
    public ResponseEntity<?> RegisterForm(RegistrationForm registrationForm){
        if(isLoggedIn()){
            registrationForm.setTrangThaiDonID(1);
            registrationFormRepository.save(registrationForm);
            return new ResponseEntity<>("Gui form thanh cong", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Chưa được đăng nhập ",HttpStatus.NOT_FOUND);
        }
    }
    public ResponseEntity<?> ConfirmRegisterForm(int dondangkiID) {

        Optional<RegistrationForm> registrationForm = registrationFormRepository.findById(dondangkiID);
        if (registrationForm.isPresent()) {
            registrationForm.get().setTrangThaiDonID(1);
            registrationForm.get().setNgayXuLi(LocalDate.now());
            registrationFormRepository.save(registrationForm.get());
            return new ResponseEntity<>("Xu li thanh cong ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("khong tim thay ", HttpStatus.NOT_FOUND);
        }
    }
    public boolean isLoggedIn(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication!=null && authentication.isAuthenticated();
    }
    public User UpdateToken(String username){
        Optional<User> user = userRepository.FindByName(username);
        user.get().setUpdatePasswordToken(UUID.randomUUID().toString());
        user.get().setUpdatePasswordTokenExpiry(LocalDate.from(LocalDateTime.now().plusMinutes(600000)));
        return userRepository.save(user.get());
    }
}
