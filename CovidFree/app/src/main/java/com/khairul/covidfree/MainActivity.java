package com.khairul.covidfree;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leo.simplearcloader.SimpleArcLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvTanggal, tvCases,tvRecovered,tvCritical,tvActive,tvTodayCases,tvTotalDeaths,tvTodayDeaths,tvAffectedCountries, tvRecoveredToday, tvKasusUp, tvsembuh, tvMeninggal;
    SimpleArcLoader simpleArcLoader, simpleArcLoader2;
    ScrollView scrollView;
    //    PieChart pieChart;
    PieChart pieChart;
    List<PieEntry> yValues = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    String  pola = "EEEE, dd MMMM YYYY";;
    String hasil = null;
    Date tanggal = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat(pola);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTanggal = findViewById(R.id.tanggal);
        hasil = formatter.format(tanggal);
        tvTanggal.setText(hasil);

        tvCases = findViewById(R.id.tvCases);
        tvRecovered = findViewById(R.id.tvRecovered);
        tvRecoveredToday = findViewById(R.id.tvRecoveredToday);
        tvCritical = findViewById(R.id.tvCritical);
        tvActive = findViewById(R.id.tvActive);
        tvTodayCases = findViewById(R.id.tvTodayCases);
        tvTotalDeaths = findViewById(R.id.tvTotalDeaths);
        tvTodayDeaths = findViewById(R.id.tvTodayDeaths);
        tvAffectedCountries = findViewById(R.id.tvAffectedCountries);
        tvKasusUp = findViewById(R.id.kasus_up);
        tvsembuh = findViewById(R.id.sembuh);
        tvMeninggal = findViewById(R.id.meninggal);

        simpleArcLoader = findViewById(R.id.loader);
        simpleArcLoader2 = findViewById(R.id.loader2);
        scrollView = findViewById(R.id.scrollStats);
//        pieChart = findViewById(R.id.piechart);
        pieChart = findViewById(R.id.pieChart);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.global);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.global:
                        return true;
                    case R.id.country:
                        startActivity(new Intent(MainActivity.this, CountryActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tips:
                        startActivity(new Intent(MainActivity.this, TIpsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        fetchData();

    }

    private void fetchData() {

        String url  = "https://corona.lmao.ninja/v2/all/";

        simpleArcLoader.start();
        simpleArcLoader2.start();

        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());

                            tvCases.setText(jsonObject.getString("cases"));
                            tvRecovered.setText(jsonObject.getString("recovered"));
                            tvRecoveredToday.setText(jsonObject.getString("todayRecovered"));
                            tvCritical.setText(jsonObject.getString("critical"));
                            tvActive.setText(jsonObject.getString("active"));
                            tvTodayCases.setText(jsonObject.getString("todayCases"));
                            tvTotalDeaths.setText(jsonObject.getString("deaths"));
                            tvTodayDeaths.setText(jsonObject.getString("todayDeaths"));
                            tvAffectedCountries.setText(jsonObject.getString("affectedCountries"));
                            tvKasusUp.setText(jsonObject.getString("todayCases"));
                            tvsembuh.setText(jsonObject.getString("todayRecovered"));
                            tvMeninggal.setText(jsonObject.getString("todayDeaths"));

                            yValues.add(new PieEntry(Integer.parseInt(tvCases.getText().toString()), "Kasus"));
                            yValues.add(new PieEntry(Integer.parseInt(tvRecovered.getText().toString()), "Sembuh"));
                            yValues.add(new PieEntry(Integer.parseInt(tvTotalDeaths.getText().toString()), "Meninggal"));
                            yValues.add(new PieEntry(Integer.parseInt(tvActive.getText().toString()), "Perawatan"));

                            pieChart.getDescription().setEnabled(false);
                            pieChart.setExtraOffsets(5, 10, 5, 5);

                            pieChart.setDragDecelerationFrictionCoef(0.99f);

                            pieChart.setDrawHoleEnabled(true);
                            pieChart.setHoleColor(Color.WHITE);
                            pieChart.setTransparentCircleRadius(61f);
                            pieChart.setRotationAngle(320);

                            pieChart.animateY(1500, Easing.EaseInOutCubic);
                            PieDataSet dataSet = new PieDataSet(yValues, "Global");
                            dataSet.setSliceSpace(3f);
                            dataSet.setSelectionShift(5f);
                            ValueFormatter vf = new ValueFormatter() { //value format here, here is the overridden method
                                @Override
                                public String getFormattedValue(float value) {
                                    return ""+(int)value;
                                }
                            };
                            dataSet.setValueFormatter(vf);
                            dataSet.setColors(Color.parseColor("#FFA726"),Color.parseColor("#66BB6A"),Color.parseColor("#EF5350"), Color.parseColor("#29B6F6"));

                            PieData data = new PieData(dataSet);
                            data.setValueTextSize(10f);
                            data.setValueTextColor(Color.YELLOW);

                            pieChart.setData(data);

                            simpleArcLoader.stop();
                            simpleArcLoader2.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            simpleArcLoader2.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                            pieChart.setVisibility(View.VISIBLE);


                        } catch (JSONException e) {
                            e.printStackTrace();
                            simpleArcLoader.stop();
                            simpleArcLoader2.stop();
                            simpleArcLoader.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }
    
}
