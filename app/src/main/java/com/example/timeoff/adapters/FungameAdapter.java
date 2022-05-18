package com.example.timeoff.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeoff.R;
import com.example.timeoff.models.FoodItem;
import com.example.timeoff.models.FungameItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FungameAdapter extends RecyclerView.Adapter<FungameAdapter.FunViewHolder>{

    public ArrayList<FoodItem> data;

    public FungameAdapter(ArrayList<FoodItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public FungameAdapter.FunViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.fungame_row, parent, false);
        return new FunViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FungameAdapter.FunViewHolder holder, int position) {
        holder.img.setImageResource(data.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class FunViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        Button add;

        public FunViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.image_fun);
            add = (Button) itemView.findViewById(R.id.enter);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull FunViewHolder holder, @SuppressLint("RecyclerView") int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Object item = data.get(position);
                String itemName = data.get(position).getName();
                DatabaseReference cart = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("cart");
                cart.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        cart.child(itemName).setValue(item);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}
