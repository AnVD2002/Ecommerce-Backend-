package com.example.Ecommerce.Entity.Products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "categories")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories {
    @Id
    @Column(name = "categoryid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    @Column(name = "name")
    private String name;
    @Column(name = "categoryimage")
    private String categoryImage;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "categories")
    @JsonManagedReference("category-products")
    List<Products> products;



}
