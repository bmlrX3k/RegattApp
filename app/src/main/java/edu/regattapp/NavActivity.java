package edu.regattapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class NavActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.homeNAV);
    }

    WeatherFragment weatherFragment = new WeatherFragment();
    RegattaFragment regattaFragment = new RegattaFragment();
    MoreFragment moreFragment = new MoreFragment();

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.weatherNAV:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, weatherFragment).commit();
                return true;

            case R.id.homeNAV:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, regattaFragment).commit();
                return true;

            case R.id.moreNAV:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, moreFragment).commit();
                return true;
        }
        return false;
    }
}