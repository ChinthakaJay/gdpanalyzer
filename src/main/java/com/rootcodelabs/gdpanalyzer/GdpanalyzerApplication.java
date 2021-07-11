package com.rootcodelabs.gdpanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GdpanalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GdpanalyzerApplication.class, args);
    }

}
