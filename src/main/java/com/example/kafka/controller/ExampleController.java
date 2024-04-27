package com.example.kafka.controller;

import com.example.kafka.kafka.KafkaProducerService;
import com.example.kafka.kafka.MessageProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/message-sender-app")
public class ExampleController {

    private final MessageProducer producer;

    @PostMapping("/sender")
    @ResponseStatus(HttpStatus.OK)
    public String sendOrder(@RequestBody String order) {
        log.info("Send order to kafka");
        producer.sendOrderEvent(order);
        return order;
    }
}
