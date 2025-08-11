package com.septeo.ulyses.technical.test.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

@Service
public class BrandCacheServiceImpl implements BrandCacheService {
    private static final Logger logger = LoggerFactory.getLogger(BrandCacheServiceImpl.class);

    private final Map<String, CacheEntry<Object>> cache = new ConcurrentHashMap<>();
    private final Map<String, ReentrantLock> locks = new ConcurrentHashMap<>();
    private final long ttlMillis = 300_000; // Example: 5 minutes

    /**
     * Constructs a BrandCacheServiceImpl with the specified cache properties.
     */
    public BrandCacheServiceImpl() {}

    /**
     * Retrieves a value from the cache or loads it using the provided supplier.
     * If the value is not present or expired, it will be fetched and cached.
     *
     * @param key the cache key
     * @param supplier the supplier to load the value if not present in cache
     * @param <T> the type of the value
     * @return the cached or loaded value
     */
    public <T> T getOrLoad(String key, Supplier<T> supplier) {
        CacheEntry<Object> entry = cache.get(key); // Check if the entry exists and is not expired
        if (entry != null && !entry.isExpired()) {
            return (T) entry.getValue(); // Return the cached value if it exists and is valid
        }
        logger.info("Cache miss or expired for key {}", key);
        ReentrantLock lock = locks.computeIfAbsent(key, k -> new ReentrantLock()); // Create a lock for the key
        lock.lock(); // Acquire the lock to ensure thread safety
        try {
            entry = cache.get(key);
            if (entry != null && !entry.isExpired()) { // Check again after acquiring the lock
                return (T) entry.getValue(); 
            }
            T value = supplier.get(); // Load the value using the supplier
            // Cache the new value with an expiry time
            cache.put(key, new CacheEntry<>(value, System.currentTimeMillis() + ttlMillis));
            return value;
        } finally {
            lock.unlock(); // Always release the lock to avoid deadlocks
        }
    }

    public void invalidate(String key) {
        cache.remove(key); // Remove the entry from the cache
        locks.remove(key); // Remove the lock for the key
    }
}
