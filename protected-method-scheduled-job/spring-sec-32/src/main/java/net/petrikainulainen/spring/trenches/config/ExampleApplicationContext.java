package net.petrikainulainen.spring.trenches.config;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.concurrent.DelegatingSecurityContextScheduledExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Petri Kainulainen
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = {
        "net.petrikainulainen.spring.trenches.scheduling"
})
@Import(ExampleSecurityContext.class)
@PropertySource("classpath:application.properties")
public class ExampleApplicationContext implements SchedulingConfigurer {

    private static final String ROLE = "ROLE_USER";
    private static final String USERNAME = "user";

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean
    public Executor taskExecutor() {
        ScheduledExecutorService delegateExecutor = Executors.newSingleThreadScheduledExecutor();
        SecurityContext schedulerContext = createSchedulerSecurityContext();
        return new DelegatingSecurityContextScheduledExecutorService(delegateExecutor, schedulerContext);
    }

    private SecurityContext createSchedulerSecurityContext() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(ROLE);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                USERNAME,
                ROLE,
                authorities
        );
        context.setAuthentication(authentication);

        return context;
    }

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();

        properties.setLocation(new ClassPathResource( "application.properties" ));
        properties.setIgnoreResourceNotFound(false);

        return properties;
    }
}
