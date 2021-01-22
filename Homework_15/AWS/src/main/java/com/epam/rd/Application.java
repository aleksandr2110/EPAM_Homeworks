package com.epam.rd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  // https://sysout.ru/mikroservisy-eureka-i-client-side-load-balancing/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
