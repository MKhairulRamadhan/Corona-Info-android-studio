package com.khairul.covidfree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private  int positionCountry;
    TextView tvCountry,tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvRecoveredToday, kasusUp, sembuh, meninggal, negara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        positionCountry = intent.getIntExtra("position",0);

        tvCountry = findViewById(R.id.tvCountry);
        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvRecoveredToday = findViewById(R.id.tvRecoveredToday);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        kasusUp = findViewById(R.id.kasus_up);
        sembuh = findViewById(R.id.sembuh);
        meninggal = findViewById(R.id.meninggal);
        negara = findViewById(R.id.negara);

        tvCountry.setText(CountryActivity.countryModelsList.get(positionCountry).getCountry());
        tvCases.setText(CountryActivity.countryModelsList.get(positionCountry).getCases());
        tvRecovered.setText(CountryActivity.countryModelsList.get(positionCountry).getRecovered());
        tvRecoveredToday.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayRecovered());
        tvCritical.setText(CountryActivity.countryModelsList.get(positionCountry).getCritical());
        tvActive.setText(CountryActivity.countryModelsList.get(positionCountry).getActive());
        tvTodayCases.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayCases());
        tvTotalDeaths.setText(CountryActivity.countryModelsList.get(positionCountry).getDeaths());
        tvTodayDeaths.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayDeaths());

        kasusUp.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayCases());
        sembuh.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayRecovered());
        meninggal.setText(CountryActivity.countryModelsList.get(positionCountry).getTodayDeaths());
        negara.setText(CountryActivity.countryModelsList.get(positionCountry).getCountry());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
