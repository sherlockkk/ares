package com.sherlockkk.ares;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AresApplication {

    public static void main(String[] args) {
        SpringApplication.run(AresApplication.class, args);
    }

}
