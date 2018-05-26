package com.shappy.shappy;

public class Product {

    private String name;
    private String price;
    private String distance;
    private String category;
    private String image;

    public Product(String name, String price, String distance, String category, String image) {
        this.name = name;
        this.price = price;
        this.distance = distance;
        this.category = category;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDistance() {
        return distance;
    }

    public String getCategory() {
        return category;
    }

    public String getImage() {
        return image;
    }
}
