package com.example.Ecommerce.Entity.Order;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Table(name = "orderstatusitems")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class OrderStatusItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderstatusitemsid")
    private int orderStatusItemsId;
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "orderStatusItems", fetch = FetchType.LAZY)
    @JsonManagedReference("orderStatusItem-orderItems")
    private List<OrderItem> orderItemList;
}
