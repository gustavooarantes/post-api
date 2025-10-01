package com.gustavooarantes.post_api.dto;

import java.time.LocalDateTime;

public record PostResponseDTO(
    Long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
