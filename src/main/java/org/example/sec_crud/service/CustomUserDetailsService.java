package org.example.sec_crud.service;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.CustomUserDetails;
import org.example.sec_crud.domain.entity.UserAccountEntity;
import org.example.sec_crud.domain.repository.UserAccountJpaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

// UserDetailsService -> Spring Security가 사용하는 로그인 시 의존성
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {
    private final UserAccountJpaRepository repository;

    @Override
    public @NonNull UserDetails loadUserByUsername(
            @NonNull String username) throws UsernameNotFoundException {
        // 1. db에 없는 사용자를 throw로 걸러내기
        UserAccountEntity userAccount = repository.findByUsername(username)
                .orElseThrow(NoUserException::new);
        // 2. password 등 인증 정보를 주면 그 인증정보가 제대로 되어 있는지를 Spring Security가 알아서 판단
        // https://github.com/aibe-7th/04_server_sec2/blob/main/docs/04-userdetails.md
        return CustomUserDetails.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .password(userAccount.getPassword())
                .isActive(userAccount.isActive())
                .uuid(userAccount.getUuid())
                .authorities(
                        userAccount.getRoles().stream().map(
                            role -> new SimpleGrantedAuthority(role.key())
                        ).toList()
                )
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class NoUserException extends RuntimeException { }
}
