package org.example.sec_crud.domain.entity;

import lombok.RequiredArgsConstructor;
import org.example.sec_crud.domain.dto.BoardFormDTO;
import org.example.sec_crud.domain.repository.BoardJpaRepository;
import org.example.sec_crud.domain.repository.UserAccountJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public List<BoardEntity> findAll() {
//        return repository.findAll();
        // N+1 -> entity graph
        return repository.findAllByOrderByCreatedAtDesc();
    }

    public BoardEntity findById(Long id) {
//        return repository.getReferenceById(id);
        return repository.findByIdWithWriter(id);
    }

    @Transactional
    public void update(Long userId, Long id, BoardFormDTO boardFormDTO) {
        BoardEntity board = findById(id);
        if (!board.getWriter().getId().equals(userId)) {
            throw new RuntimeException("작성자만 수정할 수 있습니다.");
        }
        board.update(boardFormDTO.title(), boardFormDTO.content());
    }
}
