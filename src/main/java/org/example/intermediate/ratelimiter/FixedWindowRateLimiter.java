package org.example.intermediate.ratelimiter;

import java.util.HashMap;
import java.util.Map;

/**
 * Fixed Window Counter Rate Limiter Algorithm
 * 
 * How it works:
 * - Divide time into fixed windows
 * - Count requests in current window
 * - Reset counter at window boundary
 * - Reject if count exceeds limit
 * 
 * Advantages:
 * - Simple implementation
 * - Low memory overhead
 * 
 * Disadvantages:
 * - Burst at window boundaries
 * 
 * Use case: Simple API rate limiting
 */
public class FixedWindowRateLimiter implements RateLimitingStrategy {
    private final int requestLimit;
    private final long windowSizeMs; // Window size in milliseconds
    private final Map<String, WindowCounter> counters;
    
    public static class WindowCounter {
        private int count;
        private long windowStart;
        
        public WindowCounter(long windowStart) {
            this.count = 0;
            this.windowStart = windowStart;
        }
    }
    
    public FixedWindowRateLimiter(int requestLimit, long windowSizeMs) {
        this.requestLimit = requestLimit;
        this.windowSizeMs = windowSizeMs;
        this.counters = new HashMap<>();
    }
    
    @Override
    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        long currentWindow = currentTime / windowSizeMs;
        
        WindowCounter counter = counters.get(userId);
        
        // Initialize or reset if window changed
        if (counter == null || counter.windowStart != currentWindow) {
            counter = new WindowCounter(currentWindow);
            counters.put(userId, counter);
        }
        
        // Check if limit exceeded
        if (counter.count >= requestLimit) {
            return false;
        }
        
        counter.count++;
        return true;
    }
    
    @Override
    public String getStrategyName() {
        return "Fixed Window (Limit: " + requestLimit + ", Window: " + windowSizeMs + "ms)";
    }
}

