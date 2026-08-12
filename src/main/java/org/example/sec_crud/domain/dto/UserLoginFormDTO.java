package org.example.sec_crud.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserLoginFormDTO(
        @NotBlank @Size(min = 4, max = 8) String username,
        @NotBlank @Size(min = 8, max = 20) String password) {
}
