package com.gustavooarantes.post_api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

public record PostRequestDTO(
    @Schema(description = "Post's title. Should not be null.", example = "Documentação da API com Swagger", requiredMode = RequiredMode.REQUIRED) String title,

    @Schema(description = "Post's content. Should not be null.", requiredMode = RequiredMode.REQUIRED) String content) {
}
