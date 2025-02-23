package com.eclock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf((csrf) -> csrf.ignoringRequestMatchers("/public/**"))
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login", "/").permitAll()
                        .requestMatchers("/logout").permitAll()
                        .requestMatchers("/public/**", "/h2-console/**").permitAll()


                        .anyRequest().authenticated()
                )

                .formLogin((form) -> form
                        .loginPage("/login")
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}