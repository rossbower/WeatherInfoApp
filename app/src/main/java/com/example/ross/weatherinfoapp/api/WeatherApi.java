package com.example.ross.weatherinfoapp.api;

    import com.example.ross.weatherinfoapp.data.weather.WeatherResult;

    import retrofit2.Call;
    import retrofit2.http.GET;
    import retrofit2.http.Query;

    public interface WeatherApi {

        @GET("weather")
        Call<WeatherResult> getWeatherResult(@Query("q") String city,
                                             @Query("units") String units,
                                             @Query("appid") String appID);
    }