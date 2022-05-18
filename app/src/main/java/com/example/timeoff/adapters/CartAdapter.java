package com.example.timeoff.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeoff.R;
import com.example.timeoff.models.Cart;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.repository.CartRepo;
import com.example.timeoff.views.MenuFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    public ArrayList<FoodItem> data;
    public Context context;
    public CartRepo cartRepo = new CartRepo();

    public CartAdapter(ArrayList<FoodItem> data) {
        this.data = data;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void update(ArrayList<FoodItem> new_data) {
        data.clear();
        data.addAll(new_data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.cart_row, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        FoodItem item = data.get(position);
        if (data.get(position).getAmount() == 0) {
            holder.name.setText(data.get(position).getName());
            holder.amount.setText("");
            holder.cost.setText("");
        }
        else {
            holder.name.setText(data.get(position).getName());
            holder.amount.setText(String.valueOf(data.get(position).getAmount()) + "шт");
            holder.cost.setText(String.valueOf(data.get(position).getCost()) + "р");
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        TextView name, amount, cost;
        ImageButton delete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_product_name);
            amount = itemView.findViewById(R.id.cart_amount_product);
            cost = itemView.findViewById(R.id.cart_cost_product);
            delete = itemView.findViewById(R.id.delete_from_cart);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.delete.setOnClickListener(new View.OnClickListener() {
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
                                    update(data);
                                }
                                else {
                                    data.remove(position);
                                    //notifyItemRemoved(position);
                                    update(data);
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
