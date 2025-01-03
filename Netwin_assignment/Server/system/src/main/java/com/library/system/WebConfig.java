package com.library.system;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@CrossOrigin
public class WebConfig implements WebMvcConfigurer{
     @Override
    public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry) {
            registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173/") 
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*") 
            .allowCredentials(true);
    }
    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads/"); 
       }
}
