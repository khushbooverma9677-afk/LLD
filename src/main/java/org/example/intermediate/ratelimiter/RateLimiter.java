package org.example.intermediate.ratelimiter;

import org.example.utils.Logger;

/**
 * API Rate Limiter using different strategies
 * 
 * Key Features:
 * - Multiple rate limiting algorithms (Token Bucket, Fixed Window)
 * - Per-user rate limiting
 * - Switchable strategies
 * - Thread-safe operations
 * 
 * Design Pattern: STRATEGY PATTERN
 * Different rate limiting algorithms can be swapped at runtime
 */
public class RateLimiter {
    private RateLimitingStrategy strategy;
    
    public RateLimiter(RateLimitingStrategy strategy) {
        this.strategy = strategy;
        Logger.info("RateLimiter initialized with: " + strategy.getStrategyName());
    }
    
    /**
     * Set the rate limiting strategy
     */
    public void setStrategy(RateLimitingStrategy strategy) {
        this.strategy = strategy;
        Logger.info("Rate limiting strategy changed to: " + strategy.getStrategyName());
    }
    
    /**
     * Check if request is allowed for a user
     */
    public boolean allowRequest(String userId) {
        return strategy.allowRequest(userId);
    }
    
    /**
     * Handle API request
     */
    public void handleRequest(String userId, String endpoint) {
        if (allowRequest(userId)) {
            Logger.success("Request allowed for " + userId + " -> " + endpoint);
        } else {
            Logger.error("Request RATE LIMITED for " + userId + " -> " + endpoint);
        }
    }
}

