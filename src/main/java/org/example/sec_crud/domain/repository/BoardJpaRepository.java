package org.example.sec_crud.domain.repository;

import org.example.sec_crud.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardJpaRepository extends JpaRepository<BoardEntity, Long> {
    @EntityGraph(attributePaths = "writer")
    List<BoardEntity> findAllByOrderByCreatedAtDesc();

    @Query("""
                SELECT b
                    FROM BoardEntity b
                    JOIN FETCH b.writer
                    WHERE b.id = :id
            """)
    BoardEntity findByIdWithWriter(Long id);
}
