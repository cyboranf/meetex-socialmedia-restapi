package com.example.meetexApi.dto.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
