package edu.miu.dtos;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "User model who commented on movie")
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
