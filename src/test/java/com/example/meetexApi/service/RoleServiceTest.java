package com.example.meetexApi.service;

import com.example.meetexApi.model.Role;
import com.example.meetexApi.repository.RoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;


import static org.mockito.Mockito.*;

class RoleServiceTest {

    private RoleService roleService;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleService = new RoleService(roleRepository);
    }

    @Test
    void delete() {
        Role role = new Role();
        role.setId(1);

        roleService.delete(role);

        verify(roleRepository).delete(role);
    }

    @Test
    void findById() {
        Long id = 1L;
        Role role = new Role();
        role.setId(Math.toIntExact(id));

        when(roleRepository.findById(id)).thenReturn(Optional.of(role));

        Optional<Role> foundRole = roleService.findById(id);

        Assert.assertTrue(foundRole.isPresent());
        verify(roleRepository).findById(id);
    }
}