package com.vigilanteaf.shortesturl.common;

import java.time.Instant;

public record ErrorResponse(
    Instant timestamp,
    int statusCode,
    String path,
    String message
) {
}
