package org.example.basics.librarymanagement;

import org.example.utils.Logger;
import java.util.HashMap;
import java.util.Map;

/**
 * Library Management System
 * 
 * Key Features:
 * - Manage books and patrons
 * - Borrow and return books
 * - Track book availability
 * - Enforce borrowing limits
 * 
 * Design Pattern: REPOSITORY PATTERN
 * Collections (HashMap) for storing books and patrons
 */
public class Library {
    private static Library instance;
    private Map<String, Book> books; // ISBN -> Book
    private Map<String, Patron> patrons; // ID -> Patron
    private Map<String, String> borrowingRecords; // BookID -> PatronID
    
    private Library() {
        this.books = new HashMap<>();
        this.patrons = new HashMap<>();
        this.borrowingRecords = new HashMap<>();
        Logger.info("Library initialized");
    }
    
    /**
     * Singleton pattern
     */
    public static Library getInstance() {
        if (instance == null) {
            synchronized (Library.class) {
                if (instance == null) {
                    instance = new Library();
                }
            }
        }
        return instance;
    }
    
    /**
     * Add a book to the library
     */
    public void addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            Logger.warn("Book already exists: " + book.getTitle());
            return;
        }
        books.put(book.getIsbn(), book);
        Logger.success("Book added: " + book.getTitle());
    }
    
    /**
     * Register a patron
     */
    public void registerPatron(Patron patron) {
        if (patrons.containsKey(patron.getId())) {
            Logger.warn("Patron already registered: " + patron.getName());
            return;
        }
        patrons.put(patron.getId(), patron);
        Logger.success("Patron registered: " + patron.getName());
    }
    
    /**
     * Borrow a book
     */
    public boolean borrowBook(String isbn, String patronId) {
        // Validate patron
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            Logger.error("Patron not found: " + patronId);
            return false;
        }
        
        if (!patron.canBorrow()) {
            Logger.error("Patron cannot borrow more books: " + patron.getName());
            return false;
        }
        
        // Validate book
        Book book = books.get(isbn);
        if (book == null) {
            Logger.error("Book not found: " + isbn);
            return false;
        }
        
        if (!book.isAvailable()) {
            Logger.error("Book not available: " + book.getTitle());
            return false;
        }
        
        // Perform borrow
        book.setAvailable(false);
        borrowingRecords.put(book.getId(), patron.getId());
        patron.incrementBorrowedBooks();
        
        Logger.success(patron.getName() + " borrowed " + book.getTitle());
        return true;
    }
    
    /**
     * Return a book
     */
    public boolean returnBook(String bookId, String patronId) {
        // Validate book
        Book book = null;
        for (Book b : books.values()) {
            if (b.getId().equals(bookId)) {
                book = b;
                break;
            }
        }
        
        if (book == null) {
            Logger.error("Book not found: " + bookId);
            return false;
        }
        
        // Validate patron
        Patron patron = patrons.get(patronId);
        if (patron == null) {
            Logger.error("Patron not found: " + patronId);
            return false;
        }
        
        // Check if book was borrowed by this patron
        if (!borrowingRecords.containsKey(bookId) || !borrowingRecords.get(bookId).equals(patronId)) {
            Logger.error("This book was not borrowed by this patron");
            return false;
        }
        
        // Perform return
        book.setAvailable(true);
        borrowingRecords.remove(bookId);
        patron.decrementBorrowedBooks();
        
        Logger.success(patron.getName() + " returned " + book.getTitle());
        return true;
    }
    
    /**
     * Search books by title
     */
    public void searchByTitle(String title) {
        System.out.println("\n========== SEARCH RESULTS ==========");
        boolean found = false;
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No books found with title: " + title);
        }
        System.out.println("====================================\n");
    }
    
    /**
     * Display library status
     */
    public void displayStatus() {
        System.out.println("\n========== LIBRARY STATUS ==========");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + books.values().stream().filter(Book::isAvailable).count());
        System.out.println("Total Patrons: " + patrons.size());
        System.out.println("Active Borrowings: " + borrowingRecords.size());
        System.out.println("====================================\n");
    }
}

