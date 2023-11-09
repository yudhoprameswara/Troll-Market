package com.trollMarket.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartRowDTO {
    private Long id;
    private String product;
    private Long quantity;
    private String shipper;
    private String seller;
    private Double totalPrice;
}
