package net.petrikainulainen.spring.trenches;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringBootExampleApplication extends WebMvcConfigurerAdapter {

    private final String ISO_DATE_FORMAT = "yyyy-MM-dd";
    private final String ISO_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new LocalDateConverter(ISO_DATE_FORMAT));
        registry.addConverter(new LocalDateTimeConverter(ISO_DATE_TIME_FORMAT));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringBootExampleApplication.class, args);
    }
}
