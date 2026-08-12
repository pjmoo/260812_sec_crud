package org.example.sec_crud.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "board")
@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity extends BaseEntity {
    // id, created_at, updated_at
    private String title;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private UserAccountEntity writer;

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
