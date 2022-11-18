package edu.baylor.cs.beargo.Config;

import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.Notification;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.server}")
    private String serveraddress;

    // Producer Config for Notification
    @Bean
    public ProducerFactory<String, Notification> NotificationProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        //configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Template for Notification
    @Bean
    public KafkaTemplate<String, Notification> NotificationkafkaTemplate() {
        return new KafkaTemplate<>(NotificationProducerFactory());
    }

    // Producer Config for Message
    @Bean
    public ProducerFactory<String, Message> MessageProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        //configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Message> MessagekafkaTemplate() {
        return new KafkaTemplate<>(MessageProducerFactory());
    }

}
