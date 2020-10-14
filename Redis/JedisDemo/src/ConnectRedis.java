import org.junit.Test;
import org.omg.PortableServer.POA;
import redis.clients.jedis.Jedis;

public class ConnectRedis {

    @Test
    public void connect01() {
        Jedis jedis = new Jedis("103.152.171.252",6379);
        String pong = jedis.ping();
        System.out.println("成功连接:" + pong);
        jedis.close();
    }

    @Test
    public void testKey() {
        Jedis jedis = new Jedis("103.152.171.252",6379);
        System.out.println(jedis.get("k1"));
    }
}
