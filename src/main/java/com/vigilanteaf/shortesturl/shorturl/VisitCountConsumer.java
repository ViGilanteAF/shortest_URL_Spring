package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VisitCountConsumer {

    private final MongoTemplate mongoTemplate;

    public VisitCountConsumer(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @KafkaListener(topics = "${shortest-url.visit-count-topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void increaseVisitCountByKey(String key) {
        mongoTemplate.updateFirst(
            Query.query(Criteria.where("key").is(key)),
            new Update().inc("visitCount", 1),
            ShortestUrlDocument.class
        );
    }
}
