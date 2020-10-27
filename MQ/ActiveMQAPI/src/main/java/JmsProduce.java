import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProduce {

    public static final String ACTIVENQ_URL = "tcp://192.168.137.133:61616";
    public static final String QUEUE_NAME = "queue-AsyncSend";

    public static void main(String[] args) throws JMSException {
        //1. 创建链接工厂，按照给定的url地址，采用默认的用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVENQ_URL);
        //2. 通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //3. 创建会话session
        // 两个参数，第一个叫事务/第二叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4. 创建目的地(具体是队列还是主题topic)
        Queue queue = session.createQueue(QUEUE_NAME);
        //5. 创建消息的生产者
        MessageProducer producer = session.createProducer(queue);
        //6. 通过使用MessageProducer产生3条消息发送到MQ的队列里面+

        /**
         * Queue默认是持久
         */
        //非持久
//        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        //持久
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);


        for (int i = 0; i < 30; i++) {
            //7. 创建消息，好比学生按照
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            //8. 通过messaProducer发送给mq
            producer.send(textMessage);
        }
        //9. 关闭资源
        producer.close();
        session.close();
        connection.close();
    }
}
