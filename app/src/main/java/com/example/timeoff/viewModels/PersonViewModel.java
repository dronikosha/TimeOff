package com.example.timeoff.viewModels;

import android.text.TextUtils;

import androidx.lifecycle.ViewModel;

import com.example.timeoff.repository.DAOUser;

public class PersonViewModel extends ViewModel {

    DAOUser daoUser = new DAOUser();


//    public boolean isValid(String name, String surname) {
//        return !TextUtils.isEmpty(name) && !TextUtils.isEmpty(surname) && !name.matches("[0-9]+") && !surname.matches("[0-9]+");
//    }

    public void addName(String name, String surname) {
        daoUser.addFIO(name, surname);
    }

}
