package com.example.kafka.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//@Data
//@Configuration
//@EnableConfigurationProperties
//@ConfigurationProperties(prefix = "kafka-configuration")
//public class KafkaProperties{
////    private String topic;
//    private String bootstrapServers;
////    private String groupId;
//
//}
