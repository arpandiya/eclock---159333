package com.eclock.seed;

import com.eclock.model.RoleEntity;
import com.eclock.model.UserEntity;
import com.eclock.repository.RoleRepository;
import com.eclock.repository.UserRepository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserSeed implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EntityManager entityManager;

    public UserSeed(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Managed entities: " +
                entityManager.getMetamodel().getEntities());
        RoleEntity admin = new RoleEntity();
        admin.setName("ROLE_ADMIN");

        RoleEntity user = new RoleEntity();
        user.setName("ROLE_USER");

        if(roleRepository.count() > 0) {
            return;
        } else {
            roleRepository.save(admin);
            roleRepository.save(user);
        }

        if(userRepository.count() > 0) {
            return;
        }
        UserEntity adminUser = new UserEntity();
        adminUser.setFirstName("Arpan");
        adminUser.setLastName("Subedi");
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("1234"));
        adminUser.setEmail("admin");
        adminUser.setRoles(List.of(admin));
        userRepository.save(adminUser);

        UserEntity normalUser = new UserEntity();
        normalUser.setFirstName("Ayden");
        normalUser.setLastName("Subedi");
        normalUser.setUsername("user");
        normalUser.setPassword(passwordEncoder.encode("1234"));
        normalUser.setEmail("user");
        normalUser.setRoles(List.of(user));
        userRepository.save(normalUser);

    }
}
