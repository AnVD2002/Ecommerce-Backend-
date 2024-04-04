package com.example.Ecommerce.Entity.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.List;

@Table(name = "sizes")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sizes {
    @Id
    @Column(name = "sizeid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sizeID;
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "sizes")
    @JsonManagedReference("sizes-productVariant")
    List<ProductVarients> productVarients;
}
