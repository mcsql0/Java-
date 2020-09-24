package net.mcsql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("net.mcsql.mapping")
@SpringBootApplication
public class Demo06CacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(Demo06CacheApplication.class, args);
	}

}
