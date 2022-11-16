package edu.baylor.cs.beargo.Config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

public class ListenerConfig {

    @Bean
    public ConsumerFactory<String, Users> TeamconsumerFactory()
    {

        Map<String, Object> config = new HashMap<>();

        // Adding the Configuration
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_users");
        config.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config,
                new StringDeserializer(),
                new JsonDeserializer<>(Users.class));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String,Users> concurrentKafkaTeamListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, Users> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(TeamconsumerFactory());
        return factory;
    }
}
