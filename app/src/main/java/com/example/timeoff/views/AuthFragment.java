package com.example.timeoff.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeoff.databinding.AuthLayoutBinding;
import com.example.timeoff.viewModels.AuthViewModel;
import com.example.timeoff.R;

import java.util.zip.Inflater;

public class AuthFragment extends Fragment {

    private AuthViewModel mViewModel;
    AuthLayoutBinding binding;


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
        return v;
    }


}