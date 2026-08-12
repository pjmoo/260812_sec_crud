package org.example.sec_crud.domain.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.sec_crud.domain.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserAccountJpaRepository extends JpaRepository<UserAccountEntity, Long> {
//    boolean existsByUsername(String username);
//
//    Optional<UserAccountEntity> findByUsername(String username);

    @Query("""
        SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END
        FROM UserAccountEntity u
        WHERE u.username = :username
        AND u.isActive = true
    """)
    boolean existsByUsername(String username);

    @Query("""
        SELECT u
        FROM UserAccountEntity u
        WHERE u.username = :username
        AND u.isActive = true
    """)
    Optional<UserAccountEntity> findByUsername(String username);
}
