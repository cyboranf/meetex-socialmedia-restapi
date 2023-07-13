package com.example.meetexApi.helper;

import com.example.meetexApi.dto.user.UserResponseDTO;
import com.example.meetexApi.mapper.CompanyPageMapper;
import com.example.meetexApi.mapper.UserMapper;
import com.example.meetexApi.model.CompanyPage;
import com.example.meetexApi.model.User;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public abstract class CompanyPageMapperHelper implements CompanyPageMapper {
        protected UserMapper userMapper;

        @Named("idToAdmins")
        public Set<User> idToAdmins(Long id) {
            if (id == null) {
                return null;
            }

            User user = new User();
            user.setId(id);
            Set<User> users = new HashSet<>();
            users.add(user);

            return users;
        }

        @Named("firstAdminId")
        public UserResponseDTO firstAdminId(CompanyPage companyPage) {
            User firstAdmin = companyPage.getAdmins().iterator().next();
            return userMapper.userToUserResponseDTO(firstAdmin);
        }
    }


