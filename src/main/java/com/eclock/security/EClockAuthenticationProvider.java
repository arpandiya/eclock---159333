package com.eclock.security;

import com.eclock.model.RoleEntity;
import com.eclock.model.UserEntity;
import com.eclock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;



@Component
public class EClockAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userEmail = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserEntity user = userRepository.findByEmail(userEmail);

        if(user != null && user.getId() > 0  && passwordEncoder.matches(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken
                    (userEmail, null, getGrantedAuthorities(user.getRoles()));
        } else {
            throw new BadCredentialsException("Invalid Email or Password !");
        }


    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }



    private List<GrantedAuthority> getGrantedAuthorities(List<RoleEntity> roles) {

       return roles.stream()
               .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
               .collect(Collectors.toList());

    }
}
