package com.example.Ecommerce.Entity.Order;

import com.example.Ecommerce.Entity.User.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity

public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int orderID;
    @Column(name = "codeorder")
    private String codeOrder;
    @Column(name = "totalprice")
    private Long totalPrice;
    @Column(name = "deliveryprice")
    private Long deliveryPrice;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "createat")
    private LocalDate createAt;
    @Column(name = "dateofpayment")
    private LocalDate dateOfPayment;
    @Column(name = "deliverydate")
    private LocalDate deliveryDate;
    @Column(name = "reservedate")
    private LocalDate receiveDate;
    @Column(name = "updateat")
    private LocalDate updateAt;
    @Column(name = "userid",insertable = false, updatable = false)
    private int UserID;
    @Column(name = "orderstatusid",insertable = false, updatable = false)
    private int orderStatusID;

    @ManyToOne
    @JsonBackReference("user-orders")
    @JoinColumn(name = "userid", insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JsonBackReference("orderstatus-orders")
    @JoinColumn(name = "orderstatusid")
    private OrderStatus orderStatus;

    @OneToMany(fetch = FetchType.LAZY , mappedBy = "order")
    @JsonManagedReference("order-orderitems")
    private List<OrderItem> orderItems;

}
