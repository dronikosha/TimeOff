package com.example.timeoff.views;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.timeoff.R;
import com.example.timeoff.databinding.RegistrFragmentBinding;
import com.example.timeoff.viewModels.RegistrViewModel;

public class RegistrFragment extends Fragment {

    private RegistrViewModel mViewModel;
    RegistrFragmentBinding binding;

    public static RegistrFragment newInstance() {
        return new RegistrFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        binding = RegistrFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        View view = inflater.inflate(R.layout.registr_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(RegistrViewModel.class);
        // TODO: Use the ViewModel
        binding.registration.setSelected(Boolean.parseBoolean("True"));
        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = binding.regMail.getText().toString();
                String password = binding.regPassword.getText().toString();
                String g = null;
                if (binding.male.isChecked()) {
                    g = "male";
                } else if (binding.female.isChecked()){
                    g = "female";
                }
                if (mViewModel.isValid(mail, password, g)) {
                    if (binding.agreement.isChecked()) {
                        mViewModel.createUser(mail, password, g, requireContext());
                        fragmentManager.beginTransaction().replace(R.id.reg_layout, new AuthFragment()).commit();
                        //Toast.makeText(requireContext(), mViewModel.getUser(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Необходимо согласие", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(requireContext(), "Неверные данные", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return v;
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