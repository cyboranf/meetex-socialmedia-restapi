package com.example.meetexApi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class UserRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 3, max = 30)
    private String userName;

    private String lastName;

    @NotBlank
    @Size(min = 6, max = 100)
    private String password;
}
