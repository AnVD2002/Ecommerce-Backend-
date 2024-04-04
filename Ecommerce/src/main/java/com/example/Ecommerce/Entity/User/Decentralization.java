package com.example.Ecommerce.Entity.User;

import com.example.Ecommerce.Enum.QuyenHanEnum;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Table(name = "phanquyen")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Decentralization {
    @Id
    @Column(name = "quyenhanid")
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int QuyenHanID;
    @Column(name= "tenquyenhan")
    @Enumerated(EnumType.STRING)
    private QuyenHanEnum TenQuyenHan;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "decentralization" )
    private List<User> user;
}
