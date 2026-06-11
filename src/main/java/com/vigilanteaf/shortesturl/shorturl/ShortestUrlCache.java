package com.vigilanteaf.shortesturl.shorturl;

import java.time.Duration;
import java.util.Optional;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShortestUrlCache {

    private final StringRedisTemplate redisTemplate;
    private final ShortestUrlProperties properties;

    public ShortestUrlCache(StringRedisTemplate redisTemplate, ShortestUrlProperties properties) {
        this.redisTemplate = redisTemplate;
        this.properties = properties;
    }

    public Optional<String> findOriginalUrl(String key) {
        return Optional.ofNullable(redisTemplate.opsForValue().get(key));
    }

    public void saveOriginalUrl(String key, String originalUrl) {
        Duration ttl = Duration.ofSeconds(properties.cacheTtlSeconds());
        redisTemplate.opsForValue().set(key, originalUrl, ttl);
    }
}
