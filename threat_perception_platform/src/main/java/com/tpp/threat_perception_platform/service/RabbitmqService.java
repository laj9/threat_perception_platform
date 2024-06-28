package com.tpp.threat_perception_platform.service;

public interface RabbitmqService {
    void createAgentQueue(String exchange, String queueName, String routingKey);

    void sendMessage(String exchange, String routingKey, String data);
}
