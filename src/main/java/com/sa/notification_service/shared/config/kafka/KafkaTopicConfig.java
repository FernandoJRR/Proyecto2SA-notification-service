package com.sa.notification_service.shared.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sap.common_lib.events.topics.TopicConstants;

@Configuration
class KafkaTopicConfig {
    @Bean
    NewTopic sendGenericMail() {
        return new NewTopic(TopicConstants.REQUEST_GENERIC_MAIL, 1, (short) 1);
    }
}
