package com.trollMarket.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductRowDTO {
    private Long id;
    private String name;
    private String category;
    private String discontinue;
    private Long sellerId;
}
