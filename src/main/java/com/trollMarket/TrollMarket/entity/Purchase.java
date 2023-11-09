package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BuyerId", insertable=false, updatable=false)
    private Buyer buyer;

    @Column(name = "BuyerId")
    private Long buyerId;

    @ManyToOne
    @JoinColumn(name = "ProductId",insertable=false, updatable=false)
    private Product product;

    @Column(name = "ProductId")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ShipmentId",insertable=false, updatable=false)
    private Shipper shipper;

    @Column(name = "ShipmentId")
    private Long shipmentId;

    @Column(name = "Quantity")
    private Long quantity;

    @Column(name = "Date")
    private LocalDateTime date;
}
