package com.trollMarket.TrollMarket.dto.shipper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ShipperRowDTO {
    private Long id;
    private String name;
    private Double price;
    private String service;
}
