package com.example.timeoff.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timeoff.R;
import com.example.timeoff.adapters.RecyclerAdapterNews;
import com.example.timeoff.databinding.NewsBinding;
import com.example.timeoff.models.Book;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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


    String[] news_description;
    int[] images = new int[]{R.drawable.news_room_background};
    RecyclerView recyclerView;
    public NewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = NewsBinding.inflate(inflater, container, false);
        View v = binding.getRoot();

        View view = inflater.inflate(R.layout.news, container, false);
        news_description = getResources().getStringArray(R.array.news_paper);
        recyclerView = view.findViewById(R.id.news_paper_view);
        Context ct = getContext();
        RecyclerAdapterNews recyclerAdapterNews = new RecyclerAdapterNews(ct, news_description, images);
        recyclerView.setAdapter(recyclerAdapterNews);
        recyclerView.setLayoutManager(new LinearLayoutManager(ct));


        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView timerText = view.findViewById(R.id.timer);
        View rectangle = view.findViewById(R.id.rectangle_timer);

        timerText.setVisibility(View.INVISIBLE);
        rectangle.setVisibility(View.INVISIBLE);


        final String[] Start_time = new String[1];
        final String[] End_time = new String[1];
        final String[] Start_hours = new String[1];
        final String[] Start_min = new String[1];
        final String[] Start_sec = new String[1];
        final String[] End_hours = new String[1];
        final String[] End_min = new String[1];
        final String[] End_sec = new String[1];

        final int[] count_Hour = new int[1];
        final int[] count_Min = new int[1];
        final int[] count_Sec = new int[1];
        final Long[] counter = new Long[1];
        DatabaseReference dbBook = FirebaseDatabase.getInstance("https://timeoff-71ea0-default-rtdb.europe-west1.firebasedatabase.app/").getReference("book");
        dbBook.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean check = false;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Book book = ds.getValue(Book.class);
                    if (book != null) {
                        Start_time[0] = book.getStart_time();
                        End_time[0] = book.getEnd_time();
                        check = true;
                    }
                }
                if (check) {
                    Start_hours[0] = String.valueOf(Start_time[0].charAt(0));
                    Start_hours[0] += Start_time[0].charAt(1);
                    System.out.println("HERE----------HOUR " + Start_hours[0] + " --------------------");
                    End_hours[0] = String.valueOf(End_time[0].charAt(0));
                    End_hours[0] += End_time[0].charAt(1);
                    System.out.println("HERE----------HOUR " + End_hours[0] + " --------------------");


                    //Minutes
                    Start_min[0] = String.valueOf(Start_time[0].charAt(3));
                    Start_min[0] += Start_time[0].charAt(4);
                    End_min[0] = String.valueOf(End_time[0].charAt(3));
                    End_min[0] += End_time[0].charAt(4);

                    //Seconds
//                    Start_sec[0] = String.valueOf(Start_time[0].charAt(6));
//                    Start_sec[0] += Start_time[0].charAt(7);
//                    End_sec[0] = String.valueOf(End_time[0].charAt(6));
//                    End_sec[0] += End_time[0].charAt(7);

                    //For timer
                    count_Hour[0] = Integer.parseInt(End_hours[0]) - Integer.parseInt(Start_hours[0]);
                    System.out.println("HERE----------HOUR COUNT " + count_Hour[0] + " --------------------");

                    count_Min[0] = Integer.parseInt(End_min[0]) - Integer.parseInt(Start_min[0]);
                    System.out.println("HERE----------MIN COUNT  " + count_Min[0] + " --------------------");

//                    count_Sec[0] = Integer.parseInt(End_sec[0]) - Integer.parseInt(Start_sec[0]);

                    counter[0] = (long) (((long) count_Hour[0] * 60 * 60 * 1000) + ((long) count_Min[0] * 60 * 1000));
                    System.out.println("HERE----------COUNTER " + counter[0] + " --------------------");

                    timerText.setVisibility(View.VISIBLE);
                    rectangle.setVisibility(View.VISIBLE);

                    new CountDownTimer(counter[0], 1000) {
                        public void onTick(long millisUntilFinished) {
                            // Used for formatting digit to be in 2 digits only
                            NumberFormat f = new DecimalFormat("00");
                            long hour = (millisUntilFinished / 3600000) % 24;
                            long min = (millisUntilFinished / 60000) % 60;
                            long sec = (millisUntilFinished / 1000) % 60;
                            timerText.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
                        }

                        // When the task is over it will print 00:00:00 there
                        public void onFinish() {
                            timerText.setText("00:00:00");
                            timerText.setVisibility(View.INVISIBLE);
                            rectangle.setVisibility(View.INVISIBLE);
                        }
                    }.start();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}