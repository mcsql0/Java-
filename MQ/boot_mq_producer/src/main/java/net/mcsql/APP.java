package net.mcsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling               //开启定时投送
public class APP {

    public static void main(String[] args) {
        SpringApplication.run(APP.class);
    }

}
