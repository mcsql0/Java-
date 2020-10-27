package Async;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.util.UUID;

public class JmsProduce_AsyncSend {
    public static final String ACTIVENQ_URL = "tcp://192.168.137.133:61616?jms.useAsyncSend=true";
    public static final String QUEUE_NAME = "queue-AsyncSend";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVENQ_URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(QUEUE_NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer) session.createProducer(queue);

        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);


        for (int i = 0; i < 3; i++) {
            //7. 创建消息，好比学生按照
            TextMessage textMessage = session.createTextMessage("msg---" + i);
            textMessage.setJMSMessageID(UUID.randomUUID().toString() + "---msg");

            //延迟发送或定时发送
            long delay = 30 * 1000;
            long period = 10 * 1000;
            int repeat = 9;
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delay);
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, period);
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, repeat);

            //8. 通过messaProducer发送给mq
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    try {
                        System.out.println(textMessage.getJMSMessageID() + "发送成功");
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onException(JMSException e) {
                    try {
                        System.out.println(textMessage.getJMSMessageID() + "发送失败");
                    } catch (JMSException je) {
                        je.printStackTrace();
                    }
                }
            });
        }
        //9. 关闭资源
        activeMQMessageProducer.close();
        session.close();
        connection.close();
    }
}
