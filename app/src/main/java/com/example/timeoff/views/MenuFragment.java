package com.example.timeoff.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.example.timeoff.R;
import com.example.timeoff.adapters.MenuAdapterPager;
import com.example.timeoff.databinding.AuthLayoutBinding;
import com.example.timeoff.databinding.MenuFoodFunBinding;
import com.google.android.material.tabs.TabLayout;

import java.nio.channels.MulticastChannel;

public class MenuFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    MenuFoodFunBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = MenuFoodFunBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        tabLayout = binding.tabLayout;
        viewPager = binding.pager;

        tabLayout.setupWithViewPager(viewPager);

        MenuAdapterPager menuAdapterPager = new MenuAdapterPager(getActivity().getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        menuAdapterPager.addFragment(new FoodFragment(), "Еда");
        menuAdapterPager.addFragment(new FunFragment(), "Развлчения");
        viewPager.setAdapter(menuAdapterPager);


        return v;

    }
}