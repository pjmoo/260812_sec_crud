package org.example.sec_crud.service;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.repository.UserAccountJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserAccountService {
    private final UserAccountJpaRepository userAccountJpaRepository;
}
