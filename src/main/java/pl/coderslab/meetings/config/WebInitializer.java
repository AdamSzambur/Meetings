package pl.coderslab.meetings.config;

import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class WebInitializer implements WebApplicationInitializer {
    // potrzebujemy tego do uzyskania listy aktualnie zalogowanych uzytkowników
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException{
        servletContext.addListener(HttpSessionEventPublisher.class);
    }
}
