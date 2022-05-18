package com.example.timeoff.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeoff.R;
import com.example.timeoff.models.Cart;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.CartRepo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodRecyclerAdapter extends RecyclerView.Adapter<FoodRecyclerAdapter.FoodViewHolder> {

    public ArrayList<FoodItem> data;

    public FoodRecyclerAdapter(ArrayList<FoodItem> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public FoodRecyclerAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.menu_food_row, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerAdapter.FoodViewHolder holder, int position) {
        holder.cost.setText(String.valueOf(data.get(position).getCost()));
        holder.img.setImageResource(data.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView cost;
        ImageButton plus, minus;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imageView6);
            cost = (TextView) itemView.findViewById(R.id.cost);
            plus = (ImageButton) itemView.findViewById(R.id.menu_button_plus);
            minus = (ImageButton) itemView.findViewById(R.id.menu_button_minus);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object item = data.get(position);
                String itemName = data.get(position).getName();
                DatabaseReference cart = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
                cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        boolean check = false;
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            FoodItem fItem = ds.getValue(FoodItem.class);
                            String id = ds.getKey();

                            if (itemName.equals(fItem.getName())) {
                                cart.child(itemName).child("amount").setValue(fItem.getAmount()+1);
                                cart.child(itemName).child("cost").setValue(fItem.getCost()+fItem.getCost()/fItem.getAmount());
                                check = true;
                            }

                        }
                        if (!check) {
                            cart.child(itemName).setValue(item);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object item = data.get(position);
                String itemName = data.get(position).getName();
                DatabaseReference cart = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
                cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot ds : snapshot.getChildren()) {
                            FoodItem fItem = ds.getValue(FoodItem.class);
                            String id = ds.getKey();

                            if (itemName.equals(fItem.getName())) {
                                if (fItem.getAmount() > 1) {
                                    cart.child(itemName).child("amount").setValue(fItem.getAmount()-1);
                                    cart.child(itemName).child("cost").setValue(fItem.getCost()-fItem.getCost()/fItem.getAmount());

                                }
                                else {
                                    cart.child(itemName).removeValue();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


    }
}
