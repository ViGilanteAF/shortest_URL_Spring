package com.vigilanteaf.shortesturl.shorturl;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public record CreateShortestUrlRequest(
    @NotBlank(message = "원본 URL을 입력하세요!")
    @URL(message = "올바른 URL을 입력하세요!")
    String originalUrl
) {
}
