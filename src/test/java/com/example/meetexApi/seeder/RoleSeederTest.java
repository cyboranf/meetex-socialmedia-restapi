package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Role;
import com.example.meetexApi.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RoleSeederTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleSeeder roleSeeder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void run_shouldCreateRoles_whenRoleRepositoryIsEmpty() throws Exception {
        when(roleRepository.count()).thenReturn(0L);

        roleSeeder.run();

        verify(roleRepository, times(3)).save(any(Role.class));
    }

    @Test
    public void run_shouldNotCreateRoles_whenRoleRepositoryIsNotEmpty() throws Exception {
        when(roleRepository.count()).thenReturn(1L);

        roleSeeder.run();

        verify(roleRepository, times(0)).save(any(Role.class));
    }
}
