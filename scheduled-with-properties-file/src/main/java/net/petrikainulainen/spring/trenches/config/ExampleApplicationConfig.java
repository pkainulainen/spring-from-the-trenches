package net.petrikainulainen.spring.trenches.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.servlet.*;

/**
 * @author Petri Kainulainen
 */
public class ExampleApplicationConfig implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(ExampleApplicationContext.class);

        //XmlWebApplicationContext rootContext = new XmlWebApplicationContext();
        //rootContext.setConfigLocation("classpath:exampleApplicationContext.xml");

        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
