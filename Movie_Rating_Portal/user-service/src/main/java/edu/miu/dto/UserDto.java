package edu.miu.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
