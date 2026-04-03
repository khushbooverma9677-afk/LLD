package org.example.intermediate.ratelimiter;

/**
 * Interface for rate limiting strategies
 * Demonstrates STRATEGY PATTERN
 */
public interface RateLimitingStrategy {
    /**
     * Check if request is allowed
     * @return true if request allowed, false if rate limited
     */
    boolean allowRequest(String userId);
    
    /**
     * Get the name of this strategy
     */
    String getStrategyName();
}

