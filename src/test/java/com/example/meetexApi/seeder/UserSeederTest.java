package com.example.meetexApi.seeder;

import com.example.meetexApi.exception.user.RoleNotFoundException;
import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserSeederTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserSeeder userSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateUsers_whenUserRepositoryIsEmpty() throws Exception {
        when(userRepository.count()).thenReturn(0L);
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(createRoleWithName("USER")));
        when(roleRepository.findByName("MOD")).thenReturn(Optional.of(createRoleWithName("MOD")));
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.of(createRoleWithName("ADMIN")));
        when(passwordEncoder.encode(anyString())).thenReturn("ENCODED_PASSWORD");

        userSeeder.run();

        verify(userRepository, times(3)).save(any(User.class));
    }

    @Test
    public void run_shouldThrowRoleNotFoundException_whenUserRoleNotFound() {
        when(userRepository.count()).thenReturn(0L);
        when(roleRepository.findByName("USER")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userSeeder.run());
    }

    @Test
    public void run_shouldThrowRoleNotFoundException_whenModRoleNotFound() {
        when(userRepository.count()).thenReturn(0L);
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(createRoleWithName("USER")));
        when(roleRepository.findByName("MOD")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userSeeder.run());
    }

    @Test
    public void run_shouldThrowRoleNotFoundException_whenAdminRoleNotFound() {
        when(userRepository.count()).thenReturn(0L);
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(createRoleWithName("USER")));
        when(roleRepository.findByName("MOD")).thenReturn(Optional.of(createRoleWithName("MOD")));
        when(roleRepository.findByName("ADMIN")).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userSeeder.run());
    }

    private Role createRoleWithName(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}
