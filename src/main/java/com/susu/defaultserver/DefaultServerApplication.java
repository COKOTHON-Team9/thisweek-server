package com.susu.defaultserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DefaultServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DefaultServerApplication.class, args);
    }

}
