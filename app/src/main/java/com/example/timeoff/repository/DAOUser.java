package com.example.timeoff.repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.timeoff.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DAOUser {

    private DatabaseReference databaseReference;

    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = db.getReference();
    }

    public DatabaseReference getDatabaseReference() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/");
        return db.getReference("user");
    }

    public void add(User user) {
        databaseReference.child("user").setValue(user);
    }

    public void addFIO(String name, String surname) {
        databaseReference.child("user").child("name").setValue(name);
        databaseReference.child("user").child("surname").setValue(surname);
    }

}
