package com.example.timeoff.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.timeoff.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.news, new NewsFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment fragment = null;
            switch (item.getItemId()){
                case R.id.nav_news:
                    fragment = new NewsFragment();
                    break;
                case R.id.nav_person:
                    fragment = new PersonFragment();
                    break;
                case R.id.nav_rooms:
                    fragment = new RoomFragment();
                    break;
                case R.id.nav_menu:
                    fragment = new MenuFragment();
                    break;
            }
            assert fragment != null;
            getSupportFragmentManager().beginTransaction().replace(R.id.news, fragment).commit();

            return true;
        });

    }
}
