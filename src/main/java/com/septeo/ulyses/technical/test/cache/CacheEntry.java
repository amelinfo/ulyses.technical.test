
package com.septeo.ulyses.technical.test.cache;

/**
 * Represents a cache entry that holds a value and its expiry time.
 *
 * @param <T> the type of the value being cached
 */
public class CacheEntry<T> {
    private final T value;
    private final long expiryTime;

    /**
     * Constructs a CacheEntry with the specified value and expiry time.
     *
     * @param value the value to be cached
     * @param expiryTime the time in milliseconds when this entry will expire
     */
    public CacheEntry(T value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }

    /**
     * Gets the value stored in this cache entry.
     *
     * @return the cached value
     */
    public T getValue() {
        return value;
    }

    /**
     * Checks if this cache entry has expired.
     *
     * @return true if the current time is greater than the expiry time, false otherwise
     */
    public boolean isExpired() {
        return System.currentTimeMillis() > expiryTime;
    }
}