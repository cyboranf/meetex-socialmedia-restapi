package com.example.meetexApi.service;

import com.example.meetexApi.model.Role;
import com.example.meetexApi.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param role
     */
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    /**
     * @param id
     * @return
     */
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}