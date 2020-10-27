package net.mcsql.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class Queue_Producer {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    Queue queue;

    public void ProducerMsg() {
        jmsMessagingTemplate.convertAndSend(queue, "====>SpringBoot-ActiveMQ");
    }

    @Scheduled(fixedDelay = 3000)
    public void ProducerMsgSheduuled() {
        jmsMessagingTemplate.convertAndSend(queue, "====>SpringBoot-ActiveMQ  ===> Sheduuled");
        System.out.println("Scheduled ==> ok");
    }
}
