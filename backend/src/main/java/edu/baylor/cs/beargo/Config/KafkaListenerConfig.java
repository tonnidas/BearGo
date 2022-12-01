package edu.baylor.cs.beargo.Config;

import edu.baylor.cs.beargo.dto.MessageDto;
import edu.baylor.cs.beargo.model.Message;
import edu.baylor.cs.beargo.model.Notification;
import edu.baylor.cs.beargo.model.TwitterModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@EnableKafka
@Configuration
//@Component
@Slf4j
public class KafkaListenerConfig {


    @Value("${kafka.server}")
    private String serveraddress;

    // Default Listener config for string
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {

        // Creating a Map of string-object pairs
        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        //config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_string");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    // Creating a Listener

    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    // --------------------------------------------------------------------------------------------
    // Kafka Listener Config for Notification Model


    @Bean
    public ConsumerFactory<String, Notification> NotificationConsumerFactory() {

        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        //config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_notification");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(Notification.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Notification> NotificationContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Notification> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(NotificationConsumerFactory());
        return factory;
    }

    // --------------------------------------------------------------------------------------------
    // Kafka Listener Config for Twitter Model
    @Bean
    public ConsumerFactory<String, TwitterModel> TwitterModelConsumerFactory() {

        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        //config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_notification");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(TwitterModel.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TwitterModel> TwitterModelContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TwitterModel> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(TwitterModelConsumerFactory());
        return factory;
    }

    // Kafka Listener Config for Message dto
    @Bean
    public ConsumerFactory<String, MessageDto> MessageDtoConsumerFactory() {

        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        //config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_msgdto");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(MessageDto.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageDto> MessageDtoContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(MessageDtoConsumerFactory());
        return factory;
    }


}
