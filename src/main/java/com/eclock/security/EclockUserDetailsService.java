package com.eclock.security;

import com.eclock.model.RoleEntity;
import com.eclock.model.UserEntity;
import com.eclock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EclockUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username);

        if(userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.isEnabled(),
                true,
                true,
                true,
                getAuthorities(userEntity.getRoles())
        );
    }


    private Collection<? extends GrantedAuthority> getAuthorities(List<RoleEntity> roles) {
        return roles.stream().map(role ->
                new SimpleGrantedAuthority("ROLE_" +role.getName()))
                .collect(Collectors.toList());
    }
}
