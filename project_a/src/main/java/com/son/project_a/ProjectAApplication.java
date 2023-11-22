package com.son.project_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ProjectAApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAApplication.class, args);
    }

}
