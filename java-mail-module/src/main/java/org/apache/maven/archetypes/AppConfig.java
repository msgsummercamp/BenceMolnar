package org.apache.maven.archetypes;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "org.apache.maven.archetypes")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
