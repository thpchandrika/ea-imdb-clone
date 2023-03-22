package edu.miu.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginCredentialDto {

    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
