package com.example.timeoff.repository;

import com.example.timeoff.models.Book;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOBook {
    private DatabaseReference databaseReference;

    public DAOBook() {
        FirebaseDatabase db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference = db.getReference();
    }


    public void addBook(Book book, String[] name) {
        databaseReference.child("book").removeValue();
        databaseReference.child("book").child(String.valueOf(name[0])).setValue(book);
    }

}
