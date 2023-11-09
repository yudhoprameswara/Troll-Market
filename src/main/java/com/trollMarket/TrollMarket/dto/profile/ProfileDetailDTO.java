package com.trollMarket.TrollMarket.dto.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDetailDTO {
    private Long id;
    private String name;
    private String address;
    private Double balance;
}
