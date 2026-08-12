package org.example.sec_crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error/**").permitAll()
                .requestMatchers("/user/join").permitAll()
                .anyRequest().authenticated() // 모든 요청에 대해서 인증 없이 접근 불허
        );
        return http.build();
    }
}
