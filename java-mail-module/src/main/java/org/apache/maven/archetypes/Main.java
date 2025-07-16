package org.apache.maven.archetypes;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Environment environment = context.getEnvironment();
        String toAddress = environment.getProperty("mail.to");

        MailService mailService = context.getBean(MailService.class);

        try {
            mailService.sendEmail(
                    toAddress,
                    "Test",
                    "If you see this message 2 times, it worked!"
            );
            System.out.println("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}