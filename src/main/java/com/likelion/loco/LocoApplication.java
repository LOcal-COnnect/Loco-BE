package com.likelion.loco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class LocoApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocoApplication.class, args);
    }

}
