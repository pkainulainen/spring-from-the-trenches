package net.petrikainulainen.spring.trenches.config;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * This class is a configuration bean that contain the configuration of our application.
 *
 * @author Petri Kainulainen
 */
@Component
public final class ApplicationProperties {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProperties.class);

    private final String name;

    private final boolean productionModeEnabled;

    private final WebProperties webProperties;

    @Autowired
    public ApplicationProperties(@Value("${app.name}") String name,
                                 @Value("${app.production.mode.enabled:false}") boolean productionModeEnabled,
                                 WebProperties webProperties) {
        this.name = name;
        this.productionModeEnabled = productionModeEnabled;
        this.webProperties = webProperties;
    }

    /**
     * Returns the name of the application.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returns true if the production mode is enabled and false otherwise.
     * @return
     */
    public boolean isProductionModeEnabled() {
        return productionModeEnabled;
    }

    /**
     * Returns the configuration properties of the web layer.
     * @return
     */
    public WebProperties getWebProperties() {
        return webProperties;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("name", this.name)
                .append("productionModeEnabled", this.productionModeEnabled)
                .append("webProperties", this.webProperties)
                .toString();
    }

    @PostConstruct
    public void writeConfigurationToLog() {
        LOGGER.info("Starting application by using configuration: {}", this);
    }
}
