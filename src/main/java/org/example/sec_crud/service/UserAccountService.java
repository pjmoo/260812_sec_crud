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
        } // 어차피 unique로 인해서 에러가 나기는 하는데 사전 작업을 해주면 좋으니까
        String encoded = passwordEncoder.encode(form.password());
        // 비밀번호를 암호화(해싱)해서 저장
        UserAccountEntity entity = UserAccountEntity.builder()
                .username(form.username())
                .password(encoded)
                .build();
        entity.getRoles().add(UserAccountRole.USER);
        repository.save(entity);
    }

    @Transactional
    public void withdraw(Long id) {
        UserAccountEntity entity = repository.findById(id).orElseThrow();
        entity.withdraw();
    }

    @RequiredArgsConstructor
    @ResponseStatus(HttpStatus.CONFLICT)
    public static class DuplicateUsernameException extends RuntimeException {
        private final String username;
    }
}
