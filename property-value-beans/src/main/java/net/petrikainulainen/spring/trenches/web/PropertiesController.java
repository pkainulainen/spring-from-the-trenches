package net.petrikainulainen.spring.trenches.web;

import net.petrikainulainen.spring.trenches.config.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller provides a method that is used to get information
 * about the runtime configuration of our application.
 *
 * @author Petri Kainulainen
 */
@RestController
final class PropertiesController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PropertiesController.class);

    private final ApplicationProperties applicationProperties;

    @Autowired
    PropertiesController(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    /**
     * Returns the configuration of our application as JSON.
     * @return
     */
    @RequestMapping(value = "/config", method = RequestMethod.GET)
    ApplicationProperties getAppConfiguration() {
        LOGGER.info("Returning application configuration.");
        return applicationProperties;
    }
}
