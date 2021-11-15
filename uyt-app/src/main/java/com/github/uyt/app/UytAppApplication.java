package com.github.uyt.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;

@SpringBootApplication(exclude = {GsonAutoConfiguration.class})
public class UytAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UytAppApplication.class, args);
    }

}
