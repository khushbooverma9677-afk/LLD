package org.example.basics.librarymanagement;

import org.example.utils.BaseEntity;

/**
 * Patron (Member) class representing a library member
 */
public class Patron extends BaseEntity {
    private String name;
    private String email;
    private String phone;
    private boolean isActive;
    private int borrowedBooksCount;
    private static final int MAX_BOOKS = 5;
    
    public Patron(String name, String email, String phone) {
        super();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.isActive = true;
        this.borrowedBooksCount = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public int getBorrowedBooksCount() {
        return borrowedBooksCount;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public boolean canBorrow() {
        return isActive && borrowedBooksCount < MAX_BOOKS;
    }
    
    public void incrementBorrowedBooks() {
        borrowedBooksCount++;
    }
    
    public void decrementBorrowedBooks() {
        if (borrowedBooksCount > 0) {
            borrowedBooksCount--;
        }
    }
    
    @Override
    public String toString() {
        return "Patron{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", borrowedBooks=" + borrowedBooksCount +
                ", isActive=" + isActive +
                '}';
    }
}

