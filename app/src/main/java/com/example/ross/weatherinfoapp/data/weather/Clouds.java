package com.example.ross.weatherinfoapp.data.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Clouds
{

    @SerializedName("lon")
    @Expose
    public Double lon;
    @SerializedName("lat")
    @Expose
    public Double lat;
}
