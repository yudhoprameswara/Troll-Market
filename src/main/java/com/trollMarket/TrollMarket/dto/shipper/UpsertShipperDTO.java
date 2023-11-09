package com.trollMarket.TrollMarket.dto.shipper;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpsertShipperDTO {

    private Long id;

    @NotBlank(message = "Nama tidak boleh Kosong")
    private String name;
    
    @Min(value = 0,message = "harga tidak 0")
    @NotNull(message = "Price tidak boleh kosong")
    private Double price;

    private String service;
}
