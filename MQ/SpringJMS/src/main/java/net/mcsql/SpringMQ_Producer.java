package net.mcsql;

import javafx.application.Application;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * 生产者
 */
@Component
public class SpringMQ_Producer {

    JmsTemplate jmsTemplate;

    @Autowired
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        SpringMQ_Producer springMQ_producer = (SpringMQ_Producer) applicationContext.getBean("springMQ_Producer");
        springMQ_producer.jmsTemplate.send((session) -> {
            TextMessage textMessage = session.createTextMessage("Spring--->");
            return textMessage;
        });
    }
}
