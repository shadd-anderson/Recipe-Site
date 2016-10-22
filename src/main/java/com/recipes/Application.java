package com.recipes;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        run(Application.class, args);
    }
}
