package com.example.kafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.ContainerTestUtils;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = {"test-topic"}, controlledShutdown = true)
public class KafkaProducerServiceTest {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Test
    void sendToTopic() throws InterruptedException {
        //String key = "key1";
        String value = "Hello!";

        kafkaProducerService.sendToTopic(value);

        // Настройка Consumer для чтения сообщений из Kafka
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("consumer-group", "false", embeddedKafkaBroker);
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", StringDeserializer.class);

        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
        ContainerTestUtils.waitForAssignment(consumerFactory.createContainer("test-topic"), 1);

        List<ConsumerRecord<String, String>> records = KafkaTestUtils.getRecords(consumerFactory.createContainer("test-topic"), 1000);

        assertThat(records).hasSize(1);

        ConsumerRecord<String, String> record = records.get(0);
        assertThat(record.value()).isEqualTo(value);
        assertThat(record.topic()).isEqualTo("test-topic");
    }
    }
}