package com.example.timeoff.models;

public class FoodItem {

    public String name;
    public int cost;
    public int image;
    public int amount;

    public FoodItem(String name, int cost, int image, int amount) {
        this.name = name;
        this.cost = cost;
        this.image = image;
        this.amount = amount;
    }

    public FoodItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
