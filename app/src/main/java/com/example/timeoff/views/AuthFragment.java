package com.example.timeoff.views;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.timeoff.databinding.AuthLayoutBinding;
import com.example.timeoff.repository.DAOUser;
import com.example.timeoff.viewModels.AuthViewModel;
import com.example.timeoff.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

import java.util.zip.Inflater;

public class AuthFragment extends Fragment {

    private AuthViewModel mViewModel;
    AuthLayoutBinding binding;
    DAOUser daoUser = new DAOUser();


    public static AuthFragment newInstance() {
        return new AuthFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AuthLayoutBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        View view = inflater.inflate(R.layout.registr_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        binding.enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daoUser.getDatabaseReference().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult().exists()) {
                                DataSnapshot dataSnapshot = task.getResult();
                                if (binding.authMail.getText().toString().equals(String.valueOf(dataSnapshot.child("mail").getValue())) && binding.authPassword.getText().toString().equals(String.valueOf(dataSnapshot.child("password").getValue()))) {
                                    Toast.makeText(getContext(), "Успешно", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getActivity(), NavActivity.class));
                                    //TODO: Переход внутрь приложения, смена экрана на новостной
                                }
                                else {
                                    Toast.makeText(getContext(), "Неверные данные", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                });
            }
        });
        return v;
    }

}