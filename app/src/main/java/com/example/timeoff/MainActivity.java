package com.example.timeoff;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.timeoff.views.RegistrFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        getSupportFragmentManager().beginTransaction().replace(R.id.test, new RegistrFragment()).commit();


//        bottomNavigationView = findViewById(R.id.bottom_navigation);
//        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, new NewsFragment()).commit();
//
//        bottomNavigationView.setOnItemSelectedListener(item -> {
//            Fragment fragment = null;
//            switch (item.getItemId()){
//                case R.id.nav_news:t
//                    fragment = new NewsFragment();
//                    break;
//                case R.id.nav_person:
//                    fragment = new PersonFragment();
//                    break;
//                case R.id.nav_rooms:
//                    fragment = new RoomFragment();
//                    break;
//                case R.id.nav_menu:
//                    fragment = new MenuFragment();
//                    break;
//            }
//            assert fragment != null;
//            getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fragment).commit();
//
//            return true;
//        });

    }
}