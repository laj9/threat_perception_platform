package com.tpp.threat_perception_platform.service.impl;

import com.tpp.threat_perception_platform.service.RabbitmqService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqServiceImpl implements RabbitmqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 主机上线时，动态创建队列
     * @param exchangeName: 交换机名称，为agent_exchange
     * @param queueName: 队列名称，为agent_mac地址_queue
     * @param routingKey: 路由键，为mac地址
     */
    @Override
    public void createAgentQueue(String exchangeName, String queueName, String routingKey) {
        // 创建消息队列
        rabbitTemplate.execute(channel -> {
            // 声明队列
            channel.queueDeclare(queueName, true, false, false, null);
            // 绑定队列到交换机
            channel.queueBind(queueName, exchangeName, routingKey);
            return null;
        });
    }

    /**
     * 将消息发送到消息队列中
     * @param exchangeName
     * @param routingKey
     * @param message
     */
    @Override
    public void sendMessage(String exchangeName, String routingKey, String message) {
        // 发送消息到队列
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
