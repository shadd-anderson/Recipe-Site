package com.recipes;

import org.h2.tools.Server;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        try {
            Server h2Server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        run(Application.class, args);
    }
}
