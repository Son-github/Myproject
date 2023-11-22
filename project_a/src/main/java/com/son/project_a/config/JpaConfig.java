package com.son.project_a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing
public class JpaConfig { // Auditing은 자동으로 생성해주게 하는 것이다. 만약, auditing도메인이 생성된다면 import를 해서 써야함

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of("son");
    }
}
