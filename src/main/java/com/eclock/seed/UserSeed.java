package com.eclock.seed;

import com.eclock.entity.RoleEntity;
import com.eclock.entity.UserEntity;
import com.eclock.repository.RoleRepository;
import com.eclock.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserSeed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserSeed(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        RoleEntity admin = new RoleEntity();
        admin.setName("ROLE_ADMIN");
        roleRepository.save(admin);

        RoleEntity user = new RoleEntity();
        user.setName("ROLE_USER");
        roleRepository.save(user);

        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("Arpan");
        adminUser.setLastName("Subedi");
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("1234"));
        adminUser.setEmail("admin");
        adminUser.setRoles(Set.of(admin));
        userRepository.save(adminUser);

        UserEntity normalUser = new UserEntity();
        normalUser.setFirstName("Ayden");
        normalUser.setLastName("Subedi");
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode("1234"));
        normalUser.setEmail("user");
        normalUser.setRoles(Set.of(user));
        userRepository.save(normalUser);



    }
}
