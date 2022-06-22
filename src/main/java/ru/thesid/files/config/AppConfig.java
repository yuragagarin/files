package ru.thesid.files.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.thesid.files.AppRunner;

@Configuration
public class AppConfig {

    @Bean
    public AppRunner appRunner() {
        return new AppRunner();
    }
}
