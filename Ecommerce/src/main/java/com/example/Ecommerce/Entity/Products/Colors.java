package com.example.Ecommerce.Entity.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "colors")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Colors {
    @Id
    @Column(name = "colorid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int colorID;
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "colors")
    @JsonManagedReference("colors-productVariant")
    List<ProductVarients> productVarients;
}
