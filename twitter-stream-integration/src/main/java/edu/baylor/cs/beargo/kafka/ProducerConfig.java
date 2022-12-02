package edu.baylor.cs.beargo.kafka;

import edu.baylor.cs.beargo.Model.TwitterModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Component;




import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Component
@Slf4j
@Configuration
public class ProducerConfig {

    @Value("${kafka.server}")
    private String serveraddress;

    // Producer Config for TwitterModel
    @Bean
    public ProducerFactory<String, TwitterModel> TwitterModelProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        //configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        configProps.put(JsonSerializer.ADD_TYPE_INFO_HEADERS,false);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Template for twitter
    @Bean
    public KafkaTemplate<String, TwitterModel> TwitterModelkafkaTemplate() {
        return new KafkaTemplate<>(TwitterModelProducerFactory());
    }

    // Producer Config for String
    @Bean
    public ProducerFactory<String, String> StringProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        //configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    // Template for twitter
    @Bean
    public KafkaTemplate<String, String> StringModelkafkaTemplate() {
        return new KafkaTemplate<>(StringProducerFactory());
    }

    // Generic template
    @Bean
    public ProducerFactory<String, Container> ContainerproducerFactory(){

        Map<String, Object> config = new HashMap<>();

        config.put(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serveraddress);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory(config);
    }

    @Bean
    public KafkaTemplate<String, Container> kafkaTemplateContainer(){
        return new KafkaTemplate<>(ContainerproducerFactory());
    }
}
