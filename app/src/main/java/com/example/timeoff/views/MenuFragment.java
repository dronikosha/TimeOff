package com.example.timeoff.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.widget.Toast;

import com.example.timeoff.R;
import com.example.timeoff.adapters.CartAdapter;
import com.example.timeoff.adapters.MenuAdapterPager;
import com.example.timeoff.databinding.AuthLayoutBinding;
import com.example.timeoff.databinding.MenuFoodFunBinding;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.CartRepo;
import com.example.timeoff.repository.FoodItemRepo;
import com.example.timeoff.viewModels.MenuViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.channels.MulticastChannel;
import java.util.ArrayList;

public class MenuFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    MenuViewModel mViewModel;
    MenuFoodFunBinding binding;
    CartAdapter cartAdapter;
    Dialog dialog;
    ArrayList<FoodItem> list;
    CartRepo cartRepo = new CartRepo();



    public void dialogView() {
        //Создание и вызова диалогового окна
        dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_cart);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // Прозрачный фон
        dialog.setCancelable(false); // нельзя закрыть кнопкой назад
        dialog.show();
        (dialog.findViewById(R.id.butn_back_from_dialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        list = new ArrayList<FoodItem>();
        DatabaseReference db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    FoodItem item = ds.getValue(FoodItem.class);
                    System.out.println("HERE--------- " + item + " ------------");
                    list.add(item);
                }
                RecyclerView cartRec = dialog.findViewById(R.id.cart_recycler);
                cartRec.setAdapter(new CartAdapter(list));
                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
                cartRec.setLayoutManager(gridLayoutManager);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        (dialog.findViewById(R.id.enter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuilder food = new StringBuilder();
                final StringBuilder games = new StringBuilder();
                final String[] id = new String[1];
                DatabaseReference db = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
                DatabaseReference dbHistory = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("history");
                DatabaseReference dbBook = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("book");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            FoodItem fItem = ds.getValue(FoodItem.class);
                            if (fItem.getAmount() == 0) {
                                games.append(fItem.getName()).append("\n");
                            }else {
                                food.append(fItem.getName()).append("\n");
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                dbBook.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            id[0] = ds.getKey() + "_" + String.valueOf((int) (Math.random() * 1000));
                        }
                        dbHistory.child(id[0]).child("games").setValue(games.toString());
                        dbHistory.child(id[0]).child("food").setValue(food.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                dialog.dismiss();
                Toast.makeText(getContext(), "Заказ оформлен", Toast.LENGTH_SHORT).show();
//                dbHistory.child(id[0]).child("food").setValue(food[0]);
//                dbHistory.child(id[0]).child("games").setValue(games[0]);
            }
        });
//        RecyclerView cartRec = dialog.findViewById(R.id.cart_recycler);
//        list = cartRepo.getCart();
//        cartAdapter = new CartAdapter(list);
        //Создание и вызова диалогового окна
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = MenuFoodFunBinding.inflate(inflater, container, false);
        View v = binding.getRoot();
        mViewModel = new ViewModelProvider(this).get(MenuViewModel.class);

        binding.openCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView();
            }
        });

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