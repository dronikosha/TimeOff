package com.example.timeoff.repository;

import com.example.timeoff.models.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOUser {

    private DatabaseReference databaseReference;

    public DAOUser() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = db.getReference();
    }
    public void add(User user) {
        databaseReference.setValue(user);
    }

}
