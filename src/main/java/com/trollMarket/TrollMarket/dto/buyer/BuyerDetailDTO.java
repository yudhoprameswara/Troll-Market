package com.trollMarket.TrollMarket.dto.buyer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDetailDTO {
    private Long id;
    private String name;
    private String address;
    private Double balance;
}
