import net.mcsql.APP;
import net.mcsql.activemq.Queue_Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest(classes = APP.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class Queue_Producer_Test {

    @Autowired
    Queue_Producer queue_producer;

    @Test
    public void test01() {
        queue_producer.ProducerMsg();
    }
}
