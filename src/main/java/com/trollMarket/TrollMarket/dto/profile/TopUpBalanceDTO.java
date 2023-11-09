package com.trollMarket.TrollMarket.dto.profile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopUpBalanceDTO {
    private Long id;

    @NotNull(message = "Nilai tidak boleh kosong")
    @Min(value = 5000,message = "Minimum top up Rp 5000")
    private Double topUp;
}
