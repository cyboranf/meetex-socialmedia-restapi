package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Mapping(target = "lastName", ignore = true)
    public abstract UserResponseDTO userToUserResponseDTO(User user);

    public abstract User userRequestDTOtoUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "lastName", ignore = true)
    public abstract User userRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest);
}
