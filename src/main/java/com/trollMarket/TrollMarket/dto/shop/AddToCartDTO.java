package com.trollMarket.TrollMarket.dto.shop;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddToCartDTO {
    private Long productId;

    @NotNull(message = "quantity cannot be null!")
    @Min(value = 1)
    @Max(value = 999)
    private Long quantity;

    private Long shipmentId;
}
