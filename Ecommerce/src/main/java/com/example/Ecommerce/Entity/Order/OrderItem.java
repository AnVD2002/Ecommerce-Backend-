package com.example.Ecommerce.Entity.Order;

import com.example.Ecommerce.Entity.Products.Products;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Table(name = "orderitem")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitem")
    private int orderItemID;
    @Column(name = "totalprice")
    private int totalPrice;
    @Column(name = "quantity")
    private int quantity;
    @Column(name = "createat")
    private LocalDate createAt;
    @Column(name = "updateat" )
    private LocalDate updateAt;
    @Column(name = "orderid", insertable = false, updatable = false)
    private int orderID;
    @Column(name = "productid", insertable = false, updatable = false)
    private int productID;
    @Column(name = "orderstatusitemsid", insertable = false, updatable = false)
    private int orderStatusItemsId;

    @ManyToOne
    @JoinColumn(name = "orderid")
    @JsonBackReference("order-orderitems")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonBackReference("order-orderitems")
    private Products products;

    @ManyToOne
    @JoinColumn(name = "orderstatusitemsid")
    @JsonBackReference("orderStatusItem-orderItems")
    private OrderStatusItems orderStatusItems;
}
