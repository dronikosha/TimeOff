package com.example.timeoff.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeoff.R;
import com.example.timeoff.models.History;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    public ArrayList<History> data;

    public HistoryAdapter(ArrayList<History> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.history_row, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.roomName.setText(data.get(position).getRoomName());
        holder.food.setText(data.get(position).getFood());
        holder.games.setText(data.get(position).getGames());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView roomName, food, games;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            roomName = (TextView) itemView.findViewById(R.id.room_name);
            food = (TextView) itemView.findViewById(R.id.food_history_list);
            games = (TextView) itemView.findViewById(R.id.food_history_list2);
        }
    }
}
