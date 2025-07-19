package org.apache.maven.archetypes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.apache.maven.archetypes")
public class AppConfig {
    public String message() {
        return "Hello Spring from @Bean!";
    }
}
