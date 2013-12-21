package net.petrikainulainen.spring.trenches.scheduling.service;

import org.springframework.stereotype.Service;

/**
 * @author Petri Kainulainen
 */
@Service
public class HelloMessageService implements MessageService {

    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
