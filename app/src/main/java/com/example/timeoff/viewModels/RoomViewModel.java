package com.example.timeoff.viewModels;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.example.timeoff.models.Book;
import com.example.timeoff.repository.DAOBook;

public class RoomViewModel extends ViewModel {

    public Book book;
    DAOBook daoBook = new DAOBook();


    public boolean isValid(CharSequence people, CharSequence date, CharSequence start_time, CharSequence end_time) {
        return !TextUtils.isEmpty(people) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(start_time) && !TextUtils.isEmpty(end_time);
    }

    public void addBook(String people, String date, String start_time, String end_time) {
        book = new Book(people, date, start_time, end_time);
        daoBook.addBook(book);
    }



}
