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
@Entity
@Table(name = "Seller")
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "Username" ,insertable = false,updatable = false)
    private Account account;

    @Column(name = "Username")
    private String username;

    @Column(name = "Name")
    private String name;

    @Column(name = "Address")
    private String address;

    @Column(name = "Balance")
    private Double balance;
}
