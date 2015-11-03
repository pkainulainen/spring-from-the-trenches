package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Petri Kainulainen
 */
@Service
class FooBarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooBarService.class);

    public void processFooBar(FooBar fooBar) {
        LOGGER.info("Processing a FooBar object: {}", fooBar);
    }
}
