package com.trollMarket.TrollMarket.dto.history;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistoryRowDTO {
    private LocalDateTime date;
    private String seller;
    private String buyer;
    private String product;
    private Long quantity;
    private String shipper;
    private Double totalPrice;
}
