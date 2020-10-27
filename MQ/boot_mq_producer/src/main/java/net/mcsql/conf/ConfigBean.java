package net.mcsql.conf;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
@EnableJms                  //开启Springboot的Jms
public class ConfigBean {

    @Value("myQueueName")
    public String queueName;

    @Bean
    public Queue queue() {
        return new ActiveMQQueue(queueName);
    }

}
