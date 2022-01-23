package org.orange.rampup.springbootstage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"org.orange.rampup.springbootstage.controllers" ,
        "org.orange.rampup.springbootstage.service" ,
        "org.orange.rampup.springbootstage.repository",
        "org.orange.rampup.springbootstage.entity"}
)
public class MainApp {
    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);

    }
}