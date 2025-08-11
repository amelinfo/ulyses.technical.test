package com.septeo.ulyses.technical.test.logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interceptor for logging API requests and responses
 */
@Component
public class ApiLoggingFilter implements HandlerInterceptor {

    // Logger configured to write to our API log file
    private static final Logger logger = LoggerFactory.getLogger("API_LOGGER");

    // Date formatter for timestamps
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    /**
     * Executed before request handling
     * Logs incoming requests and records start time
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {
        // Record start time to calculate duration later
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);

        // Log request details
        logger.info(String.format(
                "[Request] %s | %s %s", // Format: [Request] timestamp | METHOD /path
                LocalDateTime.now().format(formatter), // Current time
                request.getMethod(), // HTTP method (GET, POST, etc.)
                request.getRequestURI() // Request path (/api/endpoint)
        ));

        return true; // Continue with request processing
    }

    /**
     * Executed after request completes
     * Logs response details including processing time
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        // Retrieve start time stored in preHandle
        long startTime = (Long) request.getAttribute("startTime");
        // Calculate processing duration
        long duration = System.currentTimeMillis() - startTime;

        // Log response details
        logger.info(String.format(
                "[Response] %s | %s %s | Status: %d | Time: %dms", // Format with duration
                LocalDateTime.now().format(formatter), // Response time
                request.getMethod(), // HTTP method
                request.getRequestURI(), // Request path
                response.getStatus(), // HTTP status code (200, 404, etc.)
                duration // Processing time in milliseconds
        ));
    }

}
