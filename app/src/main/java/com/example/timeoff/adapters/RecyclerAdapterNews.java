package com.example.timeoff.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timeoff.R;

public class RecyclerAdapterNews extends RecyclerView.Adapter<RecyclerAdapterNews.NewsHolder> {

    String[] s1;
    int[] news_images;
    Context context;

    public RecyclerAdapterNews(Context ct, String[] data, int[] images) {
        context = ct;
        news_images = images;
        s1 = data;
    }


    public class NewsHolder extends RecyclerView.ViewHolder{

        TextView text_description;
        ImageView news_image;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);
            text_description = itemView.findViewById(R.id.news_text_description);
            news_image = itemView.findViewById(R.id.news_image_present);
        }
    }
    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.liner_news_row, parent, false);
        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        holder.text_description.setText(s1[position]);
        holder.news_image.setImageResource(R.drawable.news_room_background);
    }

    @Override
    public int getItemCount() {
        return s1.length;
    }
}
