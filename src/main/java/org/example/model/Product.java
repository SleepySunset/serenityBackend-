package org.example.model;

public class Product {

    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageFileName;

    public Product() {
    }

    public Product(Long id, String title, String description, double price, String imageFileName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageFileName = imageFileName;
    }

    public Product(String title, String description, double price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}

