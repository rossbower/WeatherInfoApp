package com.example.ross.weatherinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ross.weatherinfoapp.adapter.CityItemTouchHelperCallback;
import com.example.ross.weatherinfoapp.adapter.CityRecyclerAdapter;
import com.example.ross.weatherinfoapp.data.city.City;

public class MainActivity extends AppCompatActivity implements EditInterface,
         NavigationView.OnNavigationItemSelectedListener {

    public static final int REQUEST_CODE_ADD = 100;
    public static final String KEY_CITY_TO_EDIT = "KEY_CITY_TO_EDIT";
    public static final String CITY_NAME = "CITY_NAME";
    public static final int REQUEST_CODE_EDIT = 101;
    private CityRecyclerAdapter cityRecyclerAdapter;
    private RecyclerView recyclerCity;
    private int positionToEdit = -1;
    private Long idToEdit = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupUI();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            intentShowAdd.setClass(MainActivity.this, AddCityActivity.class);
            startActivityForResult(intentShowAdd, REQUEST_CODE_ADD);
        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "Created by Ross Bower", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupUI() {
        setupToolbar();
        setupFloatingActionButton();
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        recyclerCity = (RecyclerView) findViewById(
                R.id.recyclerCity);
        recyclerCity.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerCity.setLayoutManager(mLayoutManager);

        cityRecyclerAdapter = new CityRecyclerAdapter(this);

        ItemTouchHelper.Callback callback = new CityItemTouchHelperCallback(cityRecyclerAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(recyclerCity);

        recyclerCity.setAdapter(cityRecyclerAdapter);
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnAddCity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentShowAdd = new Intent();
                intentShowAdd.setClass(MainActivity.this, AddCityActivity.class);
                startActivityForResult(intentShowAdd, REQUEST_CODE_ADD);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_ADD) {
                City newCity = (City) data.getSerializableExtra(
                        AddCityActivity.KEY_CITY);

                cityRecyclerAdapter.addCity(newCity);
                recyclerCity.scrollToPosition(0);
            } else if (requestCode == REQUEST_CODE_EDIT) {

                City changedCity = (City) data.getSerializableExtra(
                        AddCityActivity.KEY_CITY);
                changedCity.setId(idToEdit);

                cityRecyclerAdapter.edit(changedCity, positionToEdit);
            }
        }
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void showEditDialog(City cityToEdit, int position) {
        positionToEdit = position;
        idToEdit = cityToEdit.getId();
        Intent intentShowEdit = new Intent();
        intentShowEdit.setClass(MainActivity.this, AddCityActivity.class);
        intentShowEdit.putExtra(KEY_CITY_TO_EDIT,cityToEdit);
        startActivityForResult(intentShowEdit, REQUEST_CODE_EDIT);
    }

}