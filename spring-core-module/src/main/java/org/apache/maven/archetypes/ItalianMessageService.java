package org.apache.maven.archetypes;

import org.springframework.stereotype.Component;

@Component("italianMessageService")
public class ItalianMessageService implements MessageService {
    @Override
    public String getMessage() {
        return "Buongiorno é a ItalianMessageService!";
    }
}
