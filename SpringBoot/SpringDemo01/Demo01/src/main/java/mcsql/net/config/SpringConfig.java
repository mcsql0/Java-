package mcsql.net.config;

import mcsql.net.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public HelloService helloService() {
        System.out.println("给SpringBoot添加组件");
        return new HelloService();
    }
}
