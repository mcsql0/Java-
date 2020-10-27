package net.mcsql.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import javax.jms.Topic;

@Component
public class Queue_Producer {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Topic topic;

    public void ProducerMsg() {
        jmsMessagingTemplate.convertAndSend(topic, "====>SpringBoot-ActiveMQ");
    }

    @Scheduled(fixedDelay = 3000)
    public void ProducerMsgSheduuled() {
        jmsMessagingTemplate.convertAndSend(topic, "====>SpringBoot-ActiveMQ  ===> Sheduuled");
        System.out.println("Scheduled ==> ok");
    }
}
