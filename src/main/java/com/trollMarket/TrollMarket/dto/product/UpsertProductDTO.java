package com.trollMarket.TrollMarket.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpsertProductDTO {
    private Long id;
    @NotBlank(message = "Nama tidak boleh Kosong")
    private String name;
    private Long sellerId;
    @NotBlank(message = "kategori tidak boleh kosong")
    private String category;
    private String description;
    private Double price;
    private Boolean discontinue;
    private MultipartFile image;
    private String imagePath;
}
