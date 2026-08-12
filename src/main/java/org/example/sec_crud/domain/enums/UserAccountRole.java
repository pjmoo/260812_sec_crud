package org.example.sec_crud.domain.enums;

public enum UserAccountRole {
    USER, ADMIN;

    public String key() {
        return "ROLE_%s".formatted(name());
    }
}
