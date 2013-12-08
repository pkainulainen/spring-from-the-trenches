package net.petrikainulainen.spring.trenches.scheduling.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @author Petri Kainulainen
 */
@Service
public class HelloMessageService implements MessageService {

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
