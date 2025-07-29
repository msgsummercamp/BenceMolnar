package com.example;

import org.springframework.stereotype.Service;

@Service("italianMessageService")
public class ItalianMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Ciao da ItalianMessageService!";
    }
}
