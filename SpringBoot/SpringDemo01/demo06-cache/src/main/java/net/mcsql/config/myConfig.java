package net.mcsql.config;

import net.mcsql.bean.Employee;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
public class myConfig {

    /**
     * 自定义 Cache key
     * @return
     */
    @Bean(value = "myKeyGenerator")
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"["+ Arrays.asList(objects).toString() +"]";
            }
        };
    }

//    @Bean
//    public RedisTemplate<Object, Employee> redisTemplate(
//            RedisConnectionFactory redisConnectionFactory) {
//        RedisTemplate<Object,Employee> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
//        Jackson2JsonRedisSerializer<Employee> ser
//                = new Jackson2JsonRedisSerializer<Employee>(Employee.class);
//        redisTemplate.setDefaultSerializer(ser);
//        return redisTemplate;
//    }
}
