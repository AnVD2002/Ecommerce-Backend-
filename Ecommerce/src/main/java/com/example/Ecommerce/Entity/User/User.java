package com.example.Ecommerce.Entity.User;

import com.example.Ecommerce.Entity.Cart.Cart;
import com.example.Ecommerce.Entity.Order.Order;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user")
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private int UserID;
    @Column(name = "tentk")
    private String TenTK;
    @Column(name = "email")
    private String Email;
    @Column(name = "ngaycapnhat")
    private LocalDate NgayCapNhat;
    @Column(name = "matkhau")
    private String MatKhau;
    @Column(name = "sdt")
    private String SoDienThoai;
    @Column(name = "quyenhanid",insertable = false,updatable = false)
    private int QuyenHanID;
    @Column(name = "updatePasswordToken")
    private String updatePasswordToken;
    @Column(name = "status")
    private boolean isConfirmed;
    @Column(name = "updatePasswordTokenExpiry")
    private LocalDate updatePasswordTokenExpiry;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "quyenhanid")
    private Decentralization decentralization;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference("user-carts")
    private List<Cart> carts;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    @JsonManagedReference("user-orders")
    List<Order> orders;

}
