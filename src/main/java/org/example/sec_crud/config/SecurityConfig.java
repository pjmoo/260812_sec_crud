package org.example.sec_crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Map;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
//        String encodingId = "bcrypt";
        String encodingId = "argon2";
        // bcrypt
        // https://github.com/aibe-7th/04_server_sec2/blob/main/docs/03-password-encoder.md
        Map<String, PasswordEncoder> encoders = Map.of(
                "bcrypt", new BCryptPasswordEncoder(),
                "scrypt", SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8(),
                "argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8()
        );
        // Spring Security 5.8 기준 패러미터 -> 권장 패러미터를 써서 보안처리
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/error/**").permitAll()
                .requestMatchers("/user/join").permitAll()
                .anyRequest().authenticated() // 모든 요청에 대해서 인증 없이 접근 불허
        ).formLogin(form -> form
                .loginPage("/user/login").permitAll()
                .loginProcessingUrl("/user/login").permitAll()
                // 나머지는 기본값으로
        );
        return http.build();
    }
}
