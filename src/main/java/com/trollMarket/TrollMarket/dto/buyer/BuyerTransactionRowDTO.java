package com.trollMarket.TrollMarket.dto.buyer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerTransactionRowDTO {
    private LocalDateTime date;
    private String product;
    private Long quantity;
    private String shipment;
    private Double totalPrice;
}
