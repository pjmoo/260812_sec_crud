package org.example.sec_crud.domain.repository;

import org.example.sec_crud.domain.entity.BoardEntity;
import org.example.sec_crud.domain.entity.UserAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
}
