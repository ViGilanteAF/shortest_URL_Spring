package com.vigilanteaf.shortesturl.shorturl;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class VisitCountProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ShortestUrlProperties properties;

    public VisitCountProducer(KafkaTemplate<String, String> kafkaTemplate, ShortestUrlProperties properties) {
        this.kafkaTemplate = kafkaTemplate;
        this.properties = properties;
    }

    public void increaseVisitCount(String key) {
        kafkaTemplate.send(properties.visitCountTopic(), key);
    }
}
