package com.closedsource.psymed.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PsymedPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PsymedPlatformApplication.class, args);
    }

}
