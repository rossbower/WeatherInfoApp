package com.example.ross.weatherinfoapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ross.weatherinfoapp.adapter.CityRecyclerAdapter;
import com.example.ross.weatherinfoapp.adapter.MainPagerAdapter;
import com.example.ross.weatherinfoapp.api.WeatherApi;
import com.example.ross.weatherinfoapp.data.weather.WeatherResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    private final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    private final String APP_ID = "ece61d755d0e7954fbe4f020e985349c";
    private final String UNITS = "imperial";

    public static final int REQUEST_CODE_ADD = 100;
    public static final String KEY_CITY_TO_EDIT = "KEY_CITY_TO_EDIT";
    public static final String CITY_NAME = "CITY_NAME";
    public static final int REQUEST_CODE_EDIT = 101;
    private CityRecyclerAdapter cityRecyclerAdapter;
    private RecyclerView recyclerCity;
    private int positionToEdit = -1;
    private Long idToEdit = null;

    private WeatherResult weatherResult;

    public WeatherResult getWeatherResult() {
        return weatherResult;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).
                build();

        String cityName = "Budapest";

        final WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResult> call = weatherApi.getWeatherResult(cityName, UNITS, APP_ID);
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                weatherResult = response.body();

                ViewPager pager = (ViewPager) findViewById(R.id.pager);
                pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Toast.makeText(DetailsActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            Intent intentShowAdd = new Intent();
            intentShowAdd.setClass(DetailsActivity.this, AddCityActivity.class);
            startActivityForResult(intentShowAdd, REQUEST_CODE_ADD);
        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "Created by Ross Bower", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
