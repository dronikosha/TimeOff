package com.example.timeoff.repository;

import com.example.timeoff.R;
import com.example.timeoff.models.FoodItem;

import java.util.ArrayList;

public class FoodItemRepo {
    ArrayList<FoodItem> holder = new ArrayList<>();

    public FoodItemRepo() {
        holder = new ArrayList<>();
        FoodItem m1 = new FoodItem("Бургер", 300, R.mipmap.burger_for_menu, 1);
        holder.add(m1);

        FoodItem m2 = new FoodItem("Котлета", 200, R.mipmap.burger_for_menu, 1);
        holder.add(m2);

        FoodItem m3 = new FoodItem("Кофе", 100, R.mipmap.burger_for_menu, 1);
        holder.add(m3);

        FoodItem m4 = new FoodItem("Вода", 50, R.mipmap.burger_for_menu, 1);
        holder.add(m4);
    }

    public ArrayList<FoodItem> getHolder() {
        return holder;
    }

    public void setHolder(ArrayList<FoodItem> holder) {
        this.holder = holder;
    }
}
