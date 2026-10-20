package com.example.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MessageClassifier messageClassifier() {
        return new KeywordClassifier();
    }

    @Bean
    public MessageRouter messageRouter(MessageClassifier classifier) {
        return new MessageRouter(classifier);
    }
}
