package com.example.Ecommerce.Entity.Products;

import com.example.Ecommerce.Entity.Cart.CartItem;
import com.example.Ecommerce.Entity.Order.Order;
import com.example.Ecommerce.Entity.Order.OrderItem;
import com.example.Ecommerce.Entity.Order.OrderStatusItems;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "products")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Products {
    @Id
    @Column(name = "productid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "categoryid",insertable = false, updatable = false)
    private int categoryID;
    @Column(name = "productimage")
    private String productImage;
    @Column(name="outstandingproduct")
    private boolean outstandingProduct;
    @Column(name = "producttypeid",insertable = false,updatable = false)
    private int productTypeID;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "products")
    @JsonManagedReference("products-productVariant")
    private List<ProductVarients> productVarients;

    @ManyToOne
    @JsonBackReference("category-products")
    @JoinColumn(name = "categoryid")
    private Categories categories;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "products")
    @JsonManagedReference("order-orderitems")
    private List<OrderItem> orderItems;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    @JsonManagedReference("products-cartitem")
    private List<CartItem> cartItems;

    @ManyToOne()
    @JsonBackReference("producttype-products")
    @JoinColumn(name = "producttypeid")
    private ProductType productType;


}
