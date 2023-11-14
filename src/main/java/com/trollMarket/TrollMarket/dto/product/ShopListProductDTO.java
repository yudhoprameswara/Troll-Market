package com.trollMarket.TrollMarket.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopListProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String imagePath;
    private String description;
}
