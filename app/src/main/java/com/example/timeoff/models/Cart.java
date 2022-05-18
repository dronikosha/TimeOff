package com.example.timeoff.models;

public class Cart {

    public String name, cost, count;

    public Cart(String name, String count, String cost) {
        this.name = name;
        this.count = count;
        this.cost = cost;
    }

    public Cart() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
