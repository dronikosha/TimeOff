package com.example.timeoff.models;

public class FungameItem {

    public int image;
    public String name;

    public FungameItem(String name, int image) {
        this.name = name;
        this.image = image;
    }

    public FungameItem() {
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
