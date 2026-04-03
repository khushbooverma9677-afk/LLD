package org.example.intermediate.ratelimiter;

import java.util.HashMap;
import java.util.Map;

/**
 * Token Bucket Rate Limiter Algorithm
 * 
 * How it works:
 * - Each user has a bucket with a certain capacity (max tokens)
 * - Tokens are added at a fixed rate (refill rate)
 * - Each request consumes tokens
 * - If bucket is empty, request is rejected
 * 
 * Advantages:
 * - Allows burst traffic
 * - Smooth token refill
 * 
 * Use case: API rate limiting
 */
public class TokenBucketRateLimiter implements RateLimitingStrategy {
    private final int capacity;
    private final int refillRate; // tokens per second
    private final Map<String, Bucket> buckets;
    
    public static class Bucket {
        private double tokens;
        private long lastRefillTime;
        
        public Bucket(int capacity) {
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }
    }
    
    public TokenBucketRateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.buckets = new HashMap<>();
    }
    
    @Override
    public synchronized boolean allowRequest(String userId) {
        Bucket bucket = buckets.computeIfAbsent(userId, k -> new Bucket(capacity));
        
        // Refill tokens based on time passed
        long currentTime = System.currentTimeMillis();
        long timePassed = currentTime - bucket.lastRefillTime;
        double tokensToAdd = (timePassed / 1000.0) * refillRate;
        
        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTime = currentTime;
        
        // Check if request can be allowed
        if (bucket.tokens >= 1) {
            bucket.tokens -= 1;
            return true;
        }
        
        return false;
    }
    
    @Override
    public String getStrategyName() {
        return "Token Bucket (Capacity: " + capacity + ", Refill: " + refillRate + "/sec)";
    }
}

