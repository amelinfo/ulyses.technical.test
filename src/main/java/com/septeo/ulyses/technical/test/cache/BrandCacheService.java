package com.septeo.ulyses.technical.test.cache;

import java.util.function.Supplier;

public interface BrandCacheService {
    /**
     * Retrieves a value from the cache or loads it using the provided supplier.
     * If the value is not present or expired, it will be fetched and cached.
     *
     * @param key the cache key
     * @param supplier the supplier to load the value if not present in cache
     * @param <T> the type of the value
     * @return the cached or loaded value
     */
    <T> T getOrLoad(String key, Supplier<T> supplier);

    /**
     * Invalidates the cache entry for the specified key.
     * This removes the entry from the cache, allowing it to be reloaded on the next request.
     *
     * @param key the cache key to invalidate
     */
    void invalidate(String key);
}
