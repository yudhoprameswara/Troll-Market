package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name =  "SellerId" )
    private Long sellerId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Category")
    private String category;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Discontinue")
    private Boolean discontinue;


}
