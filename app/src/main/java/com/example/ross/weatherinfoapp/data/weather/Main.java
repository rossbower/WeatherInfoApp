package com.example.ross.weatherinfoapp.data.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Main {

    @SerializedName("temp")
    @Expose
    public Double temp;
    @SerializedName("pressure")
    @Expose
    public Double pressure;
    @SerializedName("humidity")
    @Expose
    public Double humidity;
    @SerializedName("temp_min")
    @Expose
    public Double tempMin;
    @SerializedName("temp_max")
    @Expose
    public Double tempMax;

    /**
     *
     * @return
     * The temp
     */
    public Double getTemp() {
        return temp;
    }


    /**
     *
     * @return
     * The pressure
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     *
     * @return
     * The humidity
     */
    public Double getHumidity() {
        return humidity;
    }

    /**
     *
     * @return
     * The tempMin
     */
    public Double getTempMin() {
        return tempMin;
    }

    /**
     *
     * @return
     * The tempMax
     */
    public Double getTempMax() {
        return tempMax;
    }

}

