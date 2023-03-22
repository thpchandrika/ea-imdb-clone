package edu.miu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.miu.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class UserSaveDto {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @JsonIgnore
    private Role role = Role.USER;
    @NotBlank
    private String address1;
    private String address2;
    @NotBlank
    private String city;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String zipCode;

}
