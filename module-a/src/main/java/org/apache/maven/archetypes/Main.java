package org.apache.maven.archetypes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MessagePrinter printer = context.getBean("message", MessagePrinter.class);
        System.out.println(printer);
    }
}