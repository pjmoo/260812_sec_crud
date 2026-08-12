package org.example.sec_crud.service;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.UserJoinFormDTO;
import org.example.sec_crud.domain.entity.UserAccountEntity;
import org.example.sec_crud.domain.enums.UserAccountRole;
import org.example.sec_crud.domain.repository.UserAccountJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAccountService {
    private final UserAccountJpaRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void join(UserJoinFormDTO form) {
        if (repository.existsByUsername(form.username())) {
            throw new DuplicateUsernameException(form.username());
        }
        String encoded = passwordEncoder.encode(form.password());
        UserAccountEntity entity = UserAccountEntity.builder()
                .username(form.username())
                .password(encoded)
                .build();
        entity.getRoles().add(UserAccountRole.USER);
        repository.save(entity);
    }

    @RequiredArgsConstructor
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class DuplicateUsernameException extends RuntimeException {
        private final String username;
    }
}
