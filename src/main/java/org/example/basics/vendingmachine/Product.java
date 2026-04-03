package org.example.basics.vendingmachine;

/**
 * Product class representing an item in the vending machine
 */
public class Product {
    private String code;
    private String name;
    private double price;
    private int quantity;
    
    public Product(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public boolean isAvailable() {
        return quantity > 0;
    }
    
    public void decreaseQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

