package com.example.timeoff.repository;

import androidx.annotation.NonNull;

import com.example.timeoff.R;
import com.example.timeoff.models.FoodItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartRepo {

    public DatabaseReference databaseReference;
    public ArrayList<FoodItem> list = new ArrayList<>();

    public CartRepo() {
        DatabaseReference db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    FoodItem item = ds.getValue(FoodItem.class);
                    System.out.println("HERE--------- " + item + " ------------");
                    list.add(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    public ArrayList<FoodItem> getCart() {
//        DatabaseReference db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
//        db.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()) {
//                    FoodItem item = ds.getValue(FoodItem.class);
//                    System.out.println("HERE--------- " + item + " ------------");
//                    list.add(item);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        return list;
//    }

    public ArrayList<FoodItem> getCart() {
        return list;
    }

}
