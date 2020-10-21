package Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.IllegalFormatFlagsException;

public class JmsComsumer {

    public static final String ACTIVENQ_URL = "tcp://103.152.171.252:61616";
    public static final String QUEUE_NAME = "topic-test";
    public static int i = 0;

    public static void main(String[] args) throws JMSException, InterruptedException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVENQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        connection.setClientID("订阅者-001");

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(QUEUE_NAME);
        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic, "remark....");

        connection.start();

        durableSubscriber.setMessageListener((message) -> {
            if (message != null && message instanceof TextMessage) {
                try {
                    if (i == 3) {
                        int a = 1/0;
                    } else if (i == 20) {
                        session.commit();
                    }
                    System.out.println(((TextMessage) message).getText());
                    i++;
                } catch (JMSException e) {
                    System.out.println("出现异常进行回滚");
                    try {
                        session.rollback();
                        return;
                    } catch (JMSException ex) {
                        ex.printStackTrace();
                    }
                    i = 0;
                }
            }
        });

        System.in.read();
        durableSubscriber.close();
        session.close();
        connection.close();
    }

}
