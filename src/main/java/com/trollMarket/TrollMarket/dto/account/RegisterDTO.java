package com.trollMarket.TrollMarket.dto.account;

import com.trollMarket.TrollMarket.validation.ComparePassword;
import com.trollMarket.TrollMarket.validation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@ComparePassword(message= "password tidak match")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {

    @UniqueUsername(message = "username tidak sudah terpakai")
    @NotBlank(message = "Username tidak boleh kosong!")
    @Size(max=20 ,message = "maksimal 20 karakter!")
    private String username;

    @NotBlank(message = "Password tidak boleh kosong!")
    @Size(max=20 ,message = "maksimal 20 karakter!")
    private String password;

    @NotBlank(message = "Password tidak boleh kosong!")
    @Size(max=20 ,message = "maksimal 20 karakter!")
    private String passwordConfirmation;

    private String role;

    private Long id;

    private String name;

    private String address;

    private Double balance;
}
