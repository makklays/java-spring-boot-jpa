package com.techmatrix18;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.techmatrix18")
public class StorehouseApplication {
    //
    public static void main(String[] args) {
        SpringApplication.run(StorehouseApplication.class, args);
    }
}

