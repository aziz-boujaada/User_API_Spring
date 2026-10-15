package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"org.example.service", "org.example.repository"})
@Import(DatabaseConfig.class)
public class RootConfig {
}
