package org.apache.maven.archetypes;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Component("message")
public class MessagePrinter {
    private final MessageService messageService;

    @Autowired
    public MessagePrinter(@Qualifier("englishMessageService") MessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public String toString() {
        return messageService.getMessage();
    }
}
