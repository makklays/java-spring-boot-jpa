package com.techmatrix18;

import com.techmatrix18.model.User;
import com.techmatrix18.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication //(scanBasePackages = "com.techmatrix18.config", exclude = {jpaConfig.class })
//@PropertySource(value = { "classpath:application.properties" })
//@ComponentScan("com.techmatrix18.repository")
@ComponentScan(basePackages = { "com.techmatrix18.repository", "com.techmatrix18.service", "com.techmatrix18.web.api" })
@EntityScan("com.techmatrix18.model")
@EnableJpaRepositories("com.techmatrix18.repository")
public class StorehouseApplication {

    private static final Logger logger = LoggerFactory.getLogger(StorehouseApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(StorehouseApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {
            // save a few customers
            repository.save(new User("Jack", "Nickson", "j.nickson@hotmail.com",1));
            repository.save(new User("Chloe", "O'Brian", "j.nickson1@hotmail.com",1));
            repository.save(new User("Kim", "Bauer", "j.nickson2@hotmail.com",1));
            repository.save(new User("David", "Palmer", "j.nickson3@hotmail.com",1));
            repository.save(new User("Michelle", "Dessler", "j.nickson4@hotmail.com",1));

            // fetch all customers
            logger.info("Customers found with findAll():");
            logger.info("-------------------------------");
            repository.findAll().forEach(user -> {
                logger.info(user.toString());
            });
            logger.info("");

            // fetch an individual customer by ID
            User user = repository.getById(1L);
            logger.info("Customer found with findById(1):");
            logger.info("--------------------------------");
            logger.info(user.toString());
            logger.info("");

            // fetch customers by last name
            logger.info("Customer found with findByLastName('Nickson'):");
            logger.info("--------------------------------------------");
            repository.findByLastname("Nickson").forEach(bauer -> {
                logger.info(bauer.toString());
            });
            logger.info("");
        };
    }*/
}

