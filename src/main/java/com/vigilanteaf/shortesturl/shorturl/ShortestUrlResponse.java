package com.vigilanteaf.shortesturl.shorturl;

import java.time.Instant;

public record ShortestUrlResponse(
    String key,
    String originalUrl,
    long visitCount,
    Instant createdAt,
    Instant updatedAt
) {

    public static ShortestUrlResponse from(ShortestUrlDocument document) {
        return new ShortestUrlResponse(
            document.getKey(),
            document.getOriginalUrl(),
            document.getVisitCount(),
            document.getCreatedAt(),
            document.getUpdatedAt()
        );
    }
}
