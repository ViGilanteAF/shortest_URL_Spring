package com.vigilanteaf.shortesturl.shorturl;

import java.util.concurrent.locks.ReentrantLock;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class CountAllocator {

    private static final String COUNTER_ID = "shortest-url";

    private final ReentrantLock lock = new ReentrantLock();
    private final MongoTemplate mongoTemplate;
    private final ShortestUrlProperties properties;

    private long current = 0;
    private long end = 0;

    public CountAllocator(MongoTemplate mongoTemplate, ShortestUrlProperties properties) {
        this.mongoTemplate = mongoTemplate;
        this.properties = properties;
    }

    public long next() {
        lock.lock();
        try {
            if (current == end) {
                CountDocument count = mongoTemplate.findAndModify(
                    Query.query(Criteria.where("_id").is(COUNTER_ID)),
                    new Update().inc("current", properties.countRange()),
                    FindAndModifyOptions.options().returnNew(false).upsert(true),
                    CountDocument.class
                );
                current = count == null ? 0 : count.getCurrent();
                end = current + properties.countRange() - 1;
            } else {
                current++;
            }

            return current;
        } finally {
            lock.unlock();
        }
    }
}
