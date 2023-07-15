package com.example.meetexApi.seeder;

import com.example.meetexApi.model.Role;
import com.example.meetexApi.repository.RoleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
@Profile("seed")
public class RoleSeeder implements DatabaseSeeder {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * @param args incoming main method arguments
     * @throws Exception
     */

    /**
     * Role:
     * "1": "USER",
     * "2": "MOD",
     * "3": "ADMIN"
     */
    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("USER");

            Role modRole = new Role();
            modRole.setName("MOD");

            Role adminRole = new Role();
            adminRole.setName("ADMIN");

            // Saving to the database
            roleRepository.save(userRole);
            roleRepository.save(modRole);
            roleRepository.save(adminRole);
        }
    }
}
