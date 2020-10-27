import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class JmsComsumer {

    public static final String ACTIVENQ_URL = "tcp://192.168.137.133:61616";
    public static final String QUEUE_NAME = "queue-AsyncSend";

    public static void main(String[] args) throws JMSException, InterruptedException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory =
                new ActiveMQConnectionFactory(ACTIVENQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer messageConsumer = session.createConsumer(queue);

        //方法一
//        while (true) {
//            TextMessage textMessage = (TextMessage) messageConsumer.receive();
//            if (textMessage != null) {
//                System.out.println(textMessage.getText());
//            } else {
//                break;
//            }
//        }

        //方法二； 监听器
        messageConsumer.setMessageListener((message) -> {
            if (message != null && message instanceof TextMessage) {
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
        messageConsumer.close();
        session.close();
        connection.close();
    }

}
