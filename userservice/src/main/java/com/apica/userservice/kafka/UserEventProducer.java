package com.apica.userservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEventProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void publish(String event) {
        kafkaTemplate.send("user-events", event);
    }
}
