package net.petrikainulainen.spring.trenches;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Petri Kainulainen
 */
@RestController
final class FooBarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FooBarController.class);

    private final FooBarService fooBarService;

    @Autowired
    FooBarController(FooBarService fooBarService) {
        this.fooBarService = fooBarService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void processFooBar(FooBar fooBar) {
        LOGGER.info("Processing a FooBar object: {}", fooBar);
        fooBarService.processFooBar(fooBar);
    }
}
