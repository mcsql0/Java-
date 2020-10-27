package net.mcsql.comsumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class MyComsumer {

    @JmsListener(destination = "myQueueName")
    public void receive(TextMessage textMessage) throws JMSException {
        String textMessageText = textMessage.getText();
        System.out.println("Comsumer ===>" + textMessageText);
    }
}
