package com.example.timeoff.views;

import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.example.timeoff.R;
import com.example.timeoff.adapters.CartAdapter;
import com.example.timeoff.adapters.FoodRecyclerAdapter;
import com.example.timeoff.databinding.FoodFragmentBinding;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.FoodItemRepo;
import com.example.timeoff.viewModels.FoodViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FoodFragment extends Fragment {

    private FoodViewModel mViewModel;
    public FoodFragmentBinding binding;
    FoodRecyclerAdapter adapter;
    DatabaseReference databaseReference;
    Dialog dialog;
    FoodItemRepo foodItemRepo = new FoodItemRepo();
    public ArrayList<FoodItem> list;

    public static FoodFragment newInstance() {
        return new FoodFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FoodFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        FoodRecyclerAdapter foodRecyclerAdapter = new FoodRecyclerAdapter(foodItemRepo.getHolder());
        binding.foodRecycler.setAdapter(foodRecyclerAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        binding.foodRecycler.setLayoutManager(gridLayoutManager);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FoodViewModel.class);
        // TODO: Use the ViewModel
    }

}