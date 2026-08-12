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
        UserAccountEntity userAccount = repository.findByUsername(username)
                .orElseThrow(NoUserException::new);
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
