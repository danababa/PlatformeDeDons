package com.uca.m2.pdd.Configuration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.SessionCookieConfig;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SessionCookieConfigInitializer implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        // Configure session cookie attributes
        SessionCookieConfig sessionCookieConfig = servletContext.getSessionCookieConfig();
        sessionCookieConfig.setHttpOnly(true);    // Prevent XSS attacks
        sessionCookieConfig.setSecure(true);      // Send cookies only over HTTPS
        sessionCookieConfig.setName("SESSIONID"); // Custom session cookie name
        sessionCookieConfig.setPath("/");         // Accessible throughout the application
    }
}
