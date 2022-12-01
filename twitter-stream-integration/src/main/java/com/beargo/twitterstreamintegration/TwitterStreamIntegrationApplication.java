package com.beargo.twitterstreamintegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;

@EnableAutoConfiguration(exclude = KafkaAutoConfiguration.class)
@SpringBootApplication
public class TwitterStreamIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterStreamIntegrationApplication.class, args);
    }

}
