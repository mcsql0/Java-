package Topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {

    public static final String ACTIVENQ_URL = "tcp://192.168.137.133:61616";
    public static final String Topic_NAME = "topic-jdbc";

    public static void main(String[] args) throws JMSException {

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVENQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(Topic_NAME);
        MessageProducer producer = session.createProducer(topic);

        producer.setDeliveryMode(DeliveryMode.PERSISTENT);

        connection.start();

        for (int i = 0; i < 30; i++) {
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            producer.send(textMessage);
        }

        //在 connection.createSession(true, Session.AUTO_ACKNOWLEDGE); 中开启事务时，必须使用session.commit();来提交；
        session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}
