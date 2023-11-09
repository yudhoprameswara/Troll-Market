package com.trollMarket.TrollMarket.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileTransactionRowDTO {
    private LocalDateTime date;
    private String product;
    private Long quantity;
    private String shipment;
    private Double totalPrice;
}
