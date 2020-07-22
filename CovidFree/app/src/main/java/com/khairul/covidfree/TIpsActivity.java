package com.khairul.covidfree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TIpsActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_t_ips);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.tips);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.global:
                        startActivity(new Intent(TIpsActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.country:
                        startActivity(new Intent(TIpsActivity.this, CountryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tips:
                        return true;
                }
                return false;
            }
        });
    }
}
