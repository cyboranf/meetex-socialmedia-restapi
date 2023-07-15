package com.example.meetexApi.seeder;

import com.example.meetexApi.exception.user.RoleNotFoundException;
import com.example.meetexApi.model.Role;
import com.example.meetexApi.model.User;
import com.example.meetexApi.repository.RoleRepository;
import com.example.meetexApi.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Order(2)
@Profile("seed")
public class UserSeeder implements DatabaseSeeder {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeeder(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * User:
     * "1": {
     * "email": "user@gmail.com",
     * "userName": "User",
     * "lastName": "User",
     * "password": "User",
     * "role": "USER",
     * "friends": ["MOD", "ADMIN"]
     * },
     *
     * "2": {
     * "email": "mod@gmail.com",
     * "userName": "Mod",
     * "lastName": "Mod",
     * "password": "Mod",
     * "role": "MOD",
     * "friends": ["USER", "ADMIN"]
     * },
     *
     * "3": {
     * "email": "admin@gmail.com",
     * "userName": "Admin",
     * "lastName": "Admin",
     * "password": "Admin",
     * "role": "ADMIN",
     * "friends": ["USER", "MOD"]
     * }
     */

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {
            Role userRole = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RoleNotFoundException("Can not found role named 'USER'."));
            Role modRole = roleRepository.findByName("MOD")
                    .orElseThrow(() -> new RoleNotFoundException("Can not found role named 'MOD'."));
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new RoleNotFoundException("Can not found role named 'ADMIN'."));

            User user = createUser("user@gmail.com", "User", "User", "User", userRole);
            User mod = createUser("mod@gmail.com", "Mod", "Mod", "Mod", modRole);
            User admin = createUser("admin@gmail.com", "Admin", "Admin", "Admin", adminRole);

            user.getFriends().add(mod);
            user.getFriends().add(admin);

            mod.getFriends().add(user);
            mod.getFriends().add(admin);

            admin.getFriends().add(user);
            admin.getFriends().add(mod);

            userRepository.save(user);
            userRepository.save(mod);
            userRepository.save(admin);
        }
    }

    private User createUser(String email, String userName, String lastName, String password, Role role) {
        User user = new User();
        user.setEmail(email);
        user.setUserName(userName);
        user.setLastName(lastName);
        user.setPassword(passwordEncoder.encode(password));
        user.getRoles().add(role);
        user.setFriends(new ArrayList<>());
        return user;
    }
}