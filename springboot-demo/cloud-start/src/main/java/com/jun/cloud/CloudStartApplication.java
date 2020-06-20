package com.jun.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jun"})
public class CloudStartApplication {




    public static void main(String[] args) {
        SpringApplication.run(CloudStartApplication.class, args);

    }

}
