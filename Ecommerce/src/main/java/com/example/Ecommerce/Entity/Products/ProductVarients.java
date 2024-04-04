package com.example.Ecommerce.Entity.Products;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "productvarients")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductVarients {
    @Id
    @Column(name = "varientid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int varientID;
    @Column(name = "productid",insertable = false,updatable = false)
    private int productID;
    @Column(name = "sizeid",insertable = false,updatable = false)
    private int sizeID;
    @Column(name = "colorid",insertable = false,updatable = false)
    private int colorID;
    @Column(name = "stock")
    private int Stock;
    @Column(name = "newprice")
    private double newPrice;
    @Column(name = "oldprice")
    private double oldPrice;


    @ManyToOne
    @JoinColumn(name = "colorid")
    @JsonBackReference("colors-productVariant")
    Colors colors;

    @ManyToOne
    @JoinColumn(name = "sizeid")
    @JsonBackReference("sizes-productVariant")
    Sizes sizes;

    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonBackReference("products-productVariant")
    Products products;




}
