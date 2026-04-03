package org.example.intermediate.lrucache;

import java.util.HashMap;
import java.util.Map;

/**
 * LRU (Least Recently Used) Cache Implementation
 * 
 * Key Features:
 * - HashMap for O(1) get operations
 * - Doubly Linked List for O(1) remove and add operations
 * - Evicts least recently used item when capacity exceeded
 * - Thread-safe with synchronized methods
 * 
 * Time Complexity:
 * - get(): O(1)
 * - put(): O(1)
 * - Eviction: O(1)
 * 
 * Space Complexity: O(capacity)
 * 
 * Use case: CPU caching, web caching, session storage
 */
public class LRUCache<K, V> {
    
    /**
     * Node in doubly linked list
     */
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final Map<K, Node> cache;
    private Node head; // Most recently used
    private Node tail; // Least recently used
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        
        // Sentinel nodes
        this.head = new Node(null, null);
        this.tail = new Node(null, null);
        head.next = tail;
        tail.prev = head;
    }
    
    /**
     * Get value from cache
     * If exists, mark as most recently used
     */
    public synchronized V get(K key) {
        if (!cache.containsKey(key)) {
            return null;
        }
        
        Node node = cache.get(key);
        moveToHead(node);
        return node.value;
    }
    
    /**
     * Put key-value pair in cache
     * If key exists, update value and mark as most recently used
     * If cache is full, evict least recently used
     */
    public synchronized void put(K key, V value) {
        if (cache.containsKey(key)) {
            // Update existing node
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            // Create new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
            
            // Check capacity
            if (cache.size() > capacity) {
                evictLRU();
            }
        }
    }
    
    /**
     * Move node to head (most recently used)
     */
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    /**
     * Add node to head
     */
    private void addToHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }
    
    /**
     * Remove node from linked list
     */
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    /**
     * Evict least recently used (tail) item
     */
    private void evictLRU() {
        Node lru = tail.prev;
        removeNode(lru);
        cache.remove(lru.key);
    }
    
    /**
     * Get current size of cache
     */
    public int size() {
        return cache.size();
    }
    
    /**
     * Check if key exists in cache
     */
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }
    
    /**
     * Display cache state (for debugging)
     */
    public void displayCache() {
        System.out.println("\n========== LRU CACHE STATE ==========");
        System.out.println("Capacity: " + capacity);
        System.out.println("Current Size: " + cache.size());
        System.out.println("Items (MRU -> LRU):");
        
        Node current = head.next;
        int index = 1;
        while (current != tail) {
            System.out.println("  " + index + ". [" + current.key + " -> " + current.value + "]");
            current = current.next;
            index++;
        }
        System.out.println("=====================================\n");
    }
}

