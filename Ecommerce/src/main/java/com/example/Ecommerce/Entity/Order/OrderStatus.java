package com.example.Ecommerce.Entity.Order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "orderstatus")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderstatusid")
    private int orderStatusID;
    @Column(name = "name")
    private String name;
    @Column(name = "orderstatusitemsid", insertable = false, updatable = false)
    private int orderStatusItemsId;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "orderStatus")
    @JsonManagedReference("orderstatus-orders")
    private List<Order> orders;

}
