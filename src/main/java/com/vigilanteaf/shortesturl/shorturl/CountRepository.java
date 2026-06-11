package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CountRepository extends MongoRepository<CountDocument, String> {
}
