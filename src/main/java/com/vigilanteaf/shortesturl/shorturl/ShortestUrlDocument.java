package com.vigilanteaf.shortesturl.shorturl;

import java.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shortest_urls")
public class ShortestUrlDocument {

    @Id
    private String id;

    @Indexed(unique = true)
    private String key;

    private String originalUrl;

    private long visitCount;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    protected ShortestUrlDocument() {
    }

    public ShortestUrlDocument(String key, String originalUrl) {
        this.key = key;
        this.originalUrl = originalUrl;
        this.visitCount = 0;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public long getVisitCount() {
        return visitCount;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
