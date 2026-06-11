package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "shortest-url")
public record ShortestUrlProperties(
    long cacheTtlSeconds,
    long countRange,
    String visitCountTopic
) {
}
