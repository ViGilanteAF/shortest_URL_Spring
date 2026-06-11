package com.vigilanteaf.shortesturl.shorturl;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShortestUrlRepository extends MongoRepository<ShortestUrlDocument, String> {

    Optional<ShortestUrlDocument> findByKey(String key);
}
