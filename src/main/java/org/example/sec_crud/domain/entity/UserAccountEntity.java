package org.example.sec_crud.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.sec_crud.domain.enums.UserAccountRole;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Table(name = "user_account")
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA
@Getter
public class UserAccountEntity extends BaseEntity {
    // id, createdAt, updatedAt
    @Column(unique = true)
    private String username;
    private boolean isActive;

    @Builder.Default // 빌더 사용 시 기본값 (빈 해시셋)
    @ElementCollection(fetch = FetchType.EAGER) // 조인 시 미리 로딩 (N+1)
    @CollectionTable(
            name = "user_account_role",
            joinColumns = @JoinColumn(name = "user_account_id"))
    // 이 enum과의 연결 관계를 다대다 관계로 보고, 해당 내용에 대한 중개 테이블
    @Enumerated(EnumType.STRING) // 문자열(name)로 기록
    private Set<UserAccountRole> roles = new HashSet<>();

    // uuid
    @Column(nullable = false, unique = true, updatable = false)
    private UUID uuid;
}
