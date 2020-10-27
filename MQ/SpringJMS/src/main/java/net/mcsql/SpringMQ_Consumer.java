package net.mcsql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * 消费者
 */
@Component
public class SpringMQ_Consumer {

    @Autowired
    JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        SpringMQ_Consumer springMQ_consumer = (SpringMQ_Consumer) context.getBean("springMQ_Consumer");
        String str = (String) springMQ_consumer.jmsTemplate.receiveAndConvert();
        System.out.println("===>" + str);
    }

}
