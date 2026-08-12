package org.example.sec_crud.domain.entity;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.BoardFormDTO;
import org.example.sec_crud.domain.repository.BoardJpaRepository;
import org.example.sec_crud.domain.repository.UserAccountJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardJpaRepository repository;
    private final UserAccountJpaRepository userRepository;

    @Transactional
    public void write(Long id, BoardFormDTO boardFormDTO) {
        UserAccountEntity user = userRepository.getReferenceById(id);
        BoardEntity board = BoardEntity.builder()
                .title(boardFormDTO.title())
                .content(boardFormDTO.content())
                .writer(user)
                .build();
        repository.save(board);
    }
}
