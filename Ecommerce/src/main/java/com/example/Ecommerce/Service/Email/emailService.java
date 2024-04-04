package com.example.Ecommerce.Service.Email;

import com.example.Ecommerce.DTO.Request.ConfirmRequest;
import com.example.Ecommerce.DTO.Request.RegisterRequest;
import com.example.Ecommerce.DTO.Response.RegisterResponse;
import com.example.Ecommerce.Entity.User.Decentralization;
import com.example.Ecommerce.Entity.User.User;
import com.example.Ecommerce.Enum.QuyenHanEnum;
import com.example.Ecommerce.Repository.UserRepo.DecentralizationRepository;
import com.example.Ecommerce.Repository.UserRepo.UserRepository;
import com.example.Ecommerce.Untils.OTPuntils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class emailService implements IemailService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private DecentralizationRepository decentralizationRepository;
    @Override
    @Transactional
    public ResponseEntity<?> XacNhanUser(ConfirmRequest confirmRequest) {
        Optional<User> userCheck = userRepository.FindByName(confirmRequest.getRegisterResponse().getUsername());
        if(userCheck.isEmpty()){
            if(confirmRequest.getCode().equals(confirmRequest.getRegisterResponse().getCode())){
                Optional<Decentralization> decentralization = decentralizationRepository.findByAuthorityName(QuyenHanEnum.USER.toString());
                if (decentralization.isEmpty()) {
                    return new ResponseEntity<>("Khong ton tai ", HttpStatus.NOT_FOUND);
                }
                User user = new User();
                user.setEmail(confirmRequest.getRegisterResponse().getEmail());
                user.setDecentralization(decentralization.get());
                user.setTenTK(confirmRequest.getRegisterResponse().getUsername());
                user.setSoDienThoai(confirmRequest.getRegisterResponse().getPhoneNumber());
                user.setMatKhau(passwordEncoder.encode(confirmRequest.getRegisterResponse().getPassword()));
                user.setConfirmed(true);
                userRepository.save(user);
                return new ResponseEntity<>("Tao tk thanh cong ", HttpStatus.OK);
            }
            return new ResponseEntity<>("Sai code", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Da ton tai tk", HttpStatus.NOT_FOUND);

    }
    @Override
    @Transactional
    public RegisterResponse Register(RegisterRequest registerRequest){
        Optional<User> userCheck = userRepository.FindByName(registerRequest.getUsername());
        OTPuntils otPuntils = new OTPuntils();
        String otp = otPuntils.GenerateOTP();
        if(userCheck.isPresent()){
            if(userCheck.get().isConfirmed()){
                return RegisterResponse.builder().message("Đã tồn tại tài khoản").build();
            }
            sendConfirmationEmail(otp, registerRequest.getEmail());
            return RegisterResponse.builder()
                    .username(registerRequest.getUsername())
                    .Email(registerRequest.getEmail())
                    .status(false)
                    .Password(passwordEncoder.encode(registerRequest.getPassword()))
                    .PhoneNumber(registerRequest.getSdt())
                    .code(otp)
                    .message("Còn 1 bước nữa để thành công")
                    .build();
        }
        sendConfirmationEmail(otp, registerRequest.getEmail());
        return RegisterResponse.builder()
                .username(registerRequest.getUsername())
                .Email(registerRequest.getEmail())
                .status(false)
                .Password(passwordEncoder.encode(registerRequest.getPassword()))
                .PhoneNumber(registerRequest.getSdt())
                .code(otp)
                .message("Còn 1 bước nữa để thành công")
                .build();
    }
    @Autowired
    public JavaMailSender javaMailSender;
    @Override
    @Transactional
    public void sendConfirmationEmail(String confirmationCode,String recipientEmail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Xác nhận đăng ký tài khoản");
        mailMessage.setText("Mã xác nhận của bạn là: " + confirmationCode);
        javaMailSender.send(mailMessage);
    }

}
