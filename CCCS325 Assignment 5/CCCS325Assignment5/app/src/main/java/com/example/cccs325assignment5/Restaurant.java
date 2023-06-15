// Student Name: Cynthia Maroupas Student ID: 261119382

package com.example.cccs325assignment5;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Restaurant {
    @SerializedName("id")
    private String id;  // Changed back to String

    @SerializedName("name")
    private String name;

    @SerializedName("image_url")
    private String imageUrl;

    @SerializedName("rating")
    private Double rating;

    @SerializedName("phone")
    private String phone;

    @SerializedName("categories")
    private List<Category> categories;

    @SerializedName("location")
    private Location location;

    @SerializedName("price")
    private String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {  // Changed back to String
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Static nested Category class
    public static class Category {
        @SerializedName("alias")
        private String alias;

        @SerializedName("title")
        private String title;


        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }

    // Static nested Location class
    public static class Location {
        @SerializedName("address1")
        private String address1;


        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

    }
}
