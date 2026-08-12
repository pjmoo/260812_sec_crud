package org.example.sec_crud.domain.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.sec_crud.domain.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountJpaRepository extends JpaRepository<UserAccountEntity, Long> {
    boolean existsByUsername(String username);
}
