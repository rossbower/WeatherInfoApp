package com.example.ross.weatherinfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.ross.weatherinfoapp.data.city.City;

public class AddCityActivity extends AppCompatActivity {

    public static final String KEY_CITY = "KEY_CITY";

    private City cityToEdit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        final EditText etCity = (EditText) findViewById(R.id.etCity);

        if (getIntent() != null
                && getIntent().hasExtra(MainActivity.KEY_CITY_TO_EDIT)) {
            etCity.setText(cityToEdit.getCityName());
        }

        Button btnSave = (Button) findViewById(R.id.btnAddCityItem);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();

                if (etCity.getText().toString().length() == 0 ) {
                    etCity.setError( "City name is required!" );
                    return;
                }

                City newCity = cityToEdit;

                if (newCity == null) {
                    newCity = new City(etCity.getText().toString());
                } else {
                    newCity.setCityName(etCity.getText().toString());
                }

                result.putExtra(KEY_CITY, newCity);

                setResult(RESULT_OK, result);
                finish();
            }
        });

    }
}