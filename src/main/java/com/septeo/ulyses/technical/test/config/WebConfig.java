package com.septeo.ulyses.technical.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.septeo.ulyses.technical.test.logger.ApiLoggingFilter;

/**
 * Configures which paths the interceptor should handle
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiLoggingFilter apiLoggingFilter;

    /**
     * Register our interceptor and apply it to API paths
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiLoggingFilter)
                .addPathPatterns("/api/**"); // Only intercept /api/** endpoints
    }
}