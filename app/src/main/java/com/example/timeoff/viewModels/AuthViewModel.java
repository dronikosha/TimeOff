package com.example.timeoff.viewModels;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.timeoff.repository.DAOUser;
import com.example.timeoff.views.AuthFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class AuthViewModel extends ViewModel {
    DAOUser daoUser = new DAOUser();
    AuthFragment authFragment;


//    public void checkUser(Context context, String mail, String pass, Intent intent) {
//        daoUser.getDatabaseReference().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (task.isSuccessful()) {
//                    if (task.getResult().exists()) {
//                        DataSnapshot dataSnapshot = task.getResult();
//                        if (mail.equals(String.valueOf(dataSnapshot.child("mail").getValue())) && pass.equals(String.valueOf(dataSnapshot.child("password").getValue()))) {
//                            Toast.makeText(context, "Nice", Toast.LENGTH_SHORT).show();
//                            authFragment.activity(intent);
//                            //TODO: Переход внутрь приложения, смена экрана на новостной
//                        }
//                    }
//                }
//            }
//        });
//    }
}
