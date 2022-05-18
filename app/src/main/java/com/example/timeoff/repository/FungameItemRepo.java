package com.example.timeoff.repository;

import com.example.timeoff.R;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.models.FungameItem;

import java.util.ArrayList;

public class FungameItemRepo {
    ArrayList<FoodItem> holder = new ArrayList<>();

    public FungameItemRepo() {
        holder = new ArrayList<>();
        FoodItem m1 = new FoodItem("Монополия", 0,R.mipmap.monopoly_for_menu, 0);
        holder.add(m1);

        FoodItem m2 = new FoodItem("Монополия2", 0, R.mipmap.monopoly_for_menu, 0);
        holder.add(m2);

        FoodItem m3 = new FoodItem("Монополия3", 0 , R.mipmap.monopoly_for_menu, 0);
        holder.add(m3);

        FoodItem m4 = new FoodItem("Монополия4", 0,  R.mipmap.monopoly_for_menu, 0);
        holder.add(m4);
    }

    public ArrayList<FoodItem> getHolder() {
        return holder;
    }

    public void setHolder(ArrayList<FoodItem> holder) {
        this.holder = holder;
    }
}
