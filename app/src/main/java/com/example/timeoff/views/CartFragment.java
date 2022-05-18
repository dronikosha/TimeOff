package com.example.timeoff.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeoff.R;
import com.example.timeoff.adapters.CartAdapter;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.CartRepo;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public ArrayList<FoodItem> list = new ArrayList<>();
    public CartRepo cartRepo = new CartRepo();
    CartAdapter cartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment=
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView cartRec = v.findViewById(R.id.cart_recycler);
        list = cartRepo.getCart();
        cartAdapter = new CartAdapter(list);
        cartRec.setAdapter(cartAdapter);
        return v;
    }
}