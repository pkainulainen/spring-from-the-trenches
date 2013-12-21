package net.petrikainulainen.spring.trenches.scheduling.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author Petri Kainulainen
 */
public interface MessageService {

    /**
     * @return The message.
     */
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getMessage();
}
