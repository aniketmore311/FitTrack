package com.aniketmore.fittrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/healthz").permitAll()
                        .requestMatchers("/user/**").hasRole("user")
                        .requestMatchers("/admin/**").hasRole("admin")
                        .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt
                                .jwtAuthenticationConverter(KeycloakRealmRoleConverter.jwtAuthenticationConverter())));

        return http.build();
    }
}