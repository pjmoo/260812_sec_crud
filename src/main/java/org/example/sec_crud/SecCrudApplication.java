package org.example.sec_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SecCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecCrudApplication.class, args);
    }

}
