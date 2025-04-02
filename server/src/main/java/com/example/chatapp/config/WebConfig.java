package com.example.chatapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allows CORS for all endpoints
                .allowedOrigins("http://localhost:3000")  // React app's URL
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")
                .allowCredentials(true); // Allows all headers
    }
}

