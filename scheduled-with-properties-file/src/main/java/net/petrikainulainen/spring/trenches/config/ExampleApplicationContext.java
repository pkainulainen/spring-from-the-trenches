package net.petrikainulainen.spring.trenches.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "net.petrikainulainen.spring.trenches.scheduling"
})
@PropertySource("classpath:application.properties")
public class ExampleApplicationContext {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

        properties.setLocation(new ClassPathResource( "application.properties" ));
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }
}
