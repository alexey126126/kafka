package com.example.kafka.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MessageProducer {
    private final KafkaProducerService kafkaMessagingService;


    public String sendOrderEvent(String m) {
        kafkaMessagingService.sendToTopic(m);
        log.info("Send message from producer {}", m);
        return m;
    }
}
