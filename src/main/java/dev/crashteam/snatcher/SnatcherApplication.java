package dev.crashteam.snatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableFeignClients
@EnableJpaRepositories(basePackages = "dev.crashteam.snatcher.repository.jdbc")
@EnableRedisRepositories(basePackages = "dev.crashteam.snatcher.repository.redis")
@SpringBootApplication
public class SnatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnatcherApplication.class, args);
    }

}
