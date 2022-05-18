package com.example.timeoff.views;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeoff.adapters.FungameAdapter;
import com.example.timeoff.databinding.FunFragmentBinding;
import com.example.timeoff.models.FungameItem;
import com.example.timeoff.repository.FungameItemRepo;
import com.example.timeoff.viewModels.FunViewModel;

public class FunFragment extends Fragment {

    private FunViewModel mViewModel;
    public FunFragmentBinding binding;
    FungameAdapter adapter;
    FungameItemRepo fungameItemRepo = new FungameItemRepo();

    public static FunFragment newInstance() {
        return new FunFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FunFragmentBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        FungameAdapter foodRecyclerAdapter = new FungameAdapter(fungameItemRepo.getHolder());
        binding.funRecycler.setAdapter(foodRecyclerAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2);
        binding.funRecycler.setLayoutManager(gridLayoutManager);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FunViewModel.class);
        // TODO: Use the ViewModel
    }

}