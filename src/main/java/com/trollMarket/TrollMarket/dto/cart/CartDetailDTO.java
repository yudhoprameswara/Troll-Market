package com.trollMarket.TrollMarket.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartDetailDTO {
    private Long id;
    private Long productId;
    private Long quantity;
    private String shipper;
    private String seller;
    private Double totalPrice;
    private Double totalPriceBuyer;

}
