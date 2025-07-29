package com.example;

import org.springframework.stereotype.Service;

@Service("englishMessageService")
public class EnglishMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Hello from EnglishMessageService!";
    }
}
