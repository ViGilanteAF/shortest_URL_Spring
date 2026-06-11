package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "counts")
public class CountDocument {

    @Id
    private String id;

    private long current;

    protected CountDocument() {
    }

    public CountDocument(String id, long current) {
        this.id = id;
        this.current = current;
    }

    public String getId() {
        return id;
    }

    public long getCurrent() {
        return current;
    }
}
