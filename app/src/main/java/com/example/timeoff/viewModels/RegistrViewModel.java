package com.example.timeoff.viewModels;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.timeoff.models.User;
import com.example.timeoff.repository.DAOUser;

public class RegistrViewModel extends ViewModel {

    public User user;
    DAOUser daoUser = new DAOUser();

    public void createUser(String mail, String password, String gender, Context a) {
        user = new User(mail, password, gender);
        daoUser.add(user);
        Toast.makeText(a, "Успешно", Toast.LENGTH_SHORT).show();
    }

    public void setEmail(String mail) {
        user.setMail(mail);
    }

    public void setPassword(String password) {
        user.setPassword(password);
    }

    public void setGender(String gender) {
        user.setGender(gender);
    }

    public String getUser() {
        return user.toString();
    }

    public boolean isValid(CharSequence mail, CharSequence password, CharSequence gender) {
        return !TextUtils.isEmpty(mail) && android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()
                && password.length() > 5 && (gender == "male" || gender == "female");
    }






    //    public MutableLiveData<String> EmailAddress = new MutableLiveData<>();
//    public MutableLiveData<String> Password = new MutableLiveData<>();
//    public MutableLiveData<String> Gender = new MutableLiveData<>();
//    private MutableLiveData<User> userMutableLiveData;
//
//
//
//    public MutableLiveData<User> getUser() {
//        if (userMutableLiveData == null) {
//            userMutableLiveData = new MutableLiveData<>();
//        }
//        return userMutableLiveData;
//    }
//
//    public MutableLiveData<String> getEmailAddress() {
//        return EmailAddress;
//    }
//
//    public void setEmailAddress(MutableLiveData<String> emailAddress) {
//        EmailAddress = emailAddress;
//    }
//
//    public MutableLiveData<String> getPassword() {
//        return Password;
//    }
//
//    public void setPassword(MutableLiveData<String> password) {
//        Password = password;
//    }
//
//    public MutableLiveData<String> getGender() {
//        return Gender;
//    }
//
//    public void setGender(MutableLiveData<String> gender) {
//        Gender = gender;
//    }
//
//    public MutableLiveData<User> getUserMutableLiveData() {
//        return userMutableLiveData;
//    }
//
//    public void setUserMutableLiveData(MutableLiveData<User> userMutableLiveData) {
//        this.userMutableLiveData = userMutableLiveData;
//    }
//
//    public void onClick(View view) {
//
//        User regUser = new User(EmailAddress.getValue(), Password.getValue(), Gender.getValue());
//        userMutableLiveData.setValue(regUser);
//
//    }
}