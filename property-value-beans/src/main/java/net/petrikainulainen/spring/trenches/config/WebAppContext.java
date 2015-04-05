package net.petrikainulainen.spring.trenches.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Petri Kainulainen
 */
@Configuration
@ComponentScan({
        "net.petrikainulainen.spring.trenches.config",
        "net.petrikainulainen.spring.trenches.web"
})
@EnableWebMvc
@PropertySource("classpath:application.properties")
public class WebAppContext {

    @Bean
    PropertySourcesPlaceholderConfigurer propertyPlaceHolderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
