package com.example.ross.weatherinfoapp.data.city;

import com.orm.SugarRecord;

import java.io.Serializable;

public class City extends SugarRecord implements Serializable{

    private String cityName;


    public City(){

    }

    public City(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
