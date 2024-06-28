package com.tpp.threat_perception_platform.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;


@Configuration
public class RabbitMQConfig {
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory){
        return new RabbitTemplate(factory);
    }
//    @Bean
//    public Queue logQueue() {
//        return new Queue("logs_queue", true);
//    }
//
//    @Bean
//    public Queue baseLineQueue() {
//        return new Queue("base_line_queue", true);
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer logContainer(ConnectionFactory connectionFactory, MessageListenerAdapter logListenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("logs_queue");
//        container.setConcurrentConsumers(5);
//        container.setMaxConcurrentConsumers(10);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener(logListenerAdapter);
//        return container;
//    }
//
//    @Bean
//    public SimpleMessageListenerContainer baseLineContainer(ConnectionFactory connectionFactory, MessageListenerAdapter baseLineListenerAdapter) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory);
//        container.setQueueNames("base_line_queue");
//        container.setConcurrentConsumers(5);
//        container.setMaxConcurrentConsumers(10);
//        container.setAcknowledgeMode(AcknowledgeMode.AUTO);
//        container.setMessageListener(baseLineListenerAdapter);
//        return container;
//    }
//
//    @Bean
//    public MessageListenerAdapter logListenerAdapter(MessageConsumer consumer) {
//        return new MessageListenerAdapter(consumer, "log");
//    }
//
//    @Bean
//    public MessageListenerAdapter baseLineListenerAdapter(MessageConsumer consumer) {
//        return new MessageListenerAdapter(consumer, "baseLineResult");
//    }
}
