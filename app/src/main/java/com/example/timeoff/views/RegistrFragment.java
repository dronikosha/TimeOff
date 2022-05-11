package com.example.timeoff.views;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.timeoff.R;
import com.example.timeoff.databinding.RegistrFragmentBinding;
import com.example.timeoff.repository.DAOUser;
import com.example.timeoff.viewModels.RegistrViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class RegistrFragment extends Fragment {

    private RegistrViewModel mViewModel;
    RegistrFragmentBinding binding;
    private NavController navController;
    public DAOUser daoUser = new DAOUser();

    public static RegistrFragment newInstance() {
        return new RegistrFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RegistrFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        View view = inflater.inflate(R.layout.registr_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(RegistrViewModel.class);
        binding.registration.setSelected(true);
        daoUser.getDatabaseReference().get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (task.getResult().exists()) {
                        startActivity(new Intent(getActivity(), NavActivity.class));
                        //TODO: Обход ввода данных аккаунта, чтобы войти в приложение, но если выйти из приложения, то он все равно потом закинет внутрь
                    }
                }
            }
        });
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_RegistrFragment_to_AuthFragment);
            }
        });
        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = binding.regMail.getText().toString();
                String password = binding.regPassword.getText().toString();
                String g = null;
                if (binding.male.isChecked()) {
                    g = "male";
                } else if (binding.female.isChecked()) {
                    g = "female";
                }
                if (mViewModel.isValid(mail, password, g)) {
                    if (binding.agreement.isChecked()) {
                        mViewModel.createUser(mail, password, g, requireContext());
                        //NavHostFragment.findNavController(fragment).navigateUp();
                        navController.navigate(R.id.action_RegistrFragment_to_AuthFragment);
                        //Toast.makeText(requireContext(), mViewModel.getUser(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Необходимо согласие", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Неверные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistrViewModel.class);
//        (getActivity().findViewById(R.id.reg)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //String result = "Done - " + mViewModel.getUser();
//                //mViewModel.setUser(mail, password, gender[0]);
//                Toast.makeText(requireContext(), "result", Toast.LENGTH_SHORT).show();
//            }
//        });
//        binding.reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //String result = "Done - " + mViewModel.getUser();
//                //mViewModel.setUser(mail, password, gender[0]);
//                Toast.makeText(requireContext(), "result", Toast.LENGTH_SHORT).show();
//            }
//        });
//        String mail = binding.regMail.getText().toString();
//        String password = binding.regPassword.getText().toString();
//        final String[] gender = new String[1];
//        RadioGroup radioGroup = (RadioGroup) binding.genderGroup;
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                // checkedId is the RadioButton selected
//                switch (checkedId) {
//                    case R.id.male:
//                        gender[0] = "Male";
//                    case R.id.female:
//                        gender[0] = "Female";
//                }
//            }
//        });
//        binding.reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String result = "Done - " + mViewModel.getUser();
//                mViewModel.setUser(mail, password, gender[0]);
//                Toast.makeText(getA, "result", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//
//    }
    }
}