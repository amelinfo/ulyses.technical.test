package com.septeo.ulyses.technical.test.config;

import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Configuration properties for the Brand caching system.
 * 
 * This class binds to properties prefixed with "cache.brand" in the application configuration.
 * It provides sensible defaults while allowing customization through external configuration.
 */
@ConfigurationProperties(prefix = "cache.brand")
@Data
public class CacheProperties {
    
    /**
     * Time-to-live for cache entries.
     * After this duration, entries are considered expired and will be removed.
     * Default: 30 minutes
     */
    private Duration ttl = Duration.ofMinutes(30);
    
    /**
     * Maximum number of entries that can be stored in the cache.
     * When this limit is reached, oldest entries will be evicted to make room for new ones.
     * Default: 1000 entries
     */
    private int maxSize = 1000;
    
    /**
     * Time before expiry when cache entries should be refreshed proactively.
     * This helps ensure users always get fresh data without waiting for database queries.
     * Default: 5 minutes before expiry
     */
    private Duration refreshAhead = Duration.ofMinutes(5);
}