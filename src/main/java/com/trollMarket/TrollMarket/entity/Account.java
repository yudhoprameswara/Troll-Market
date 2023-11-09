package com.trollMarket.TrollMarket.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name="Username")
    private String username;

    @Column(name="Password")
    private String password;

    @Column(name="Role")
    private String role;
}
