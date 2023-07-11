package com.example.meetexApi.mapper;

import com.example.meetexApi.dto.user.UserRegistrationRequest;
import com.example.meetexApi.dto.user.UserRequestDTO;
import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "password", ignore = true)
    UserResponseDTO userToUserResponseDTO(User user);

    User userRequestDTOtoUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "lastName", ignore = true)
    User userRegistrationRequestToUser(UserRegistrationRequest userRegistrationRequest);
}
