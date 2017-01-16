package com.example.ross.weatherinfoapp.fragments;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ross.weatherinfoapp.DetailsActivity;
import com.example.ross.weatherinfoapp.R;


public class FragmentTwo extends Fragment {

    private TextView tvCurrentTemp;
    private TextView tvDescription;
    private ImageView ivLogo;
    private TextView tvPressure;
    private TextView tvHumidity;
    private TextView tvTempMin;
    private TextView tvTempMax;
    private TextView tvWindSpeed;
    private TextView tvSunrise;
    private TextView tvSunset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_two, null);

        ((DetailsActivity) getActivity()).getSupportActionBar()
                .setTitle((""+((DetailsActivity)getActivity()).getWeatherResult().getName()));

        tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvCurrentTemp);
        tvCurrentTemp.setText("Current Temp: "+((DetailsActivity)getActivity()).getWeatherResult().getMain().getTemp()+"ºF");

        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        tvDescription.setText("Description: "+((DetailsActivity)getActivity()).getWeatherResult().getWeather().get(0).getMain());

        ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
        Glide.with(this).load("http://openweathermap.org/img/w/"
                +((DetailsActivity)getActivity()).getWeatherResult().getWeather().get(0).getIcon()+".png")
                .into(ivLogo);

        tvPressure = (TextView) rootView.findViewById(R.id.tvPressure);
        tvPressure.setText("Pressure: "+((DetailsActivity)getActivity()).getWeatherResult().getMain().getPressure()+" psi");

        tvHumidity = (TextView) rootView.findViewById(R.id.tvHumidity);
        tvHumidity.setText("Humidity: "+((DetailsActivity)getActivity()).getWeatherResult().getMain().getHumidity()+"%");

        tvTempMin = (TextView) rootView.findViewById(R.id.tvTempMin);
        tvTempMin.setText("Minimum Temp: "+((DetailsActivity)getActivity()).getWeatherResult().getMain().getTempMin()+"ºF");

        tvTempMax = (TextView) rootView.findViewById(R.id.tvTempMax);
        tvTempMax.setText("Maximum Temp: "+((DetailsActivity)getActivity()).getWeatherResult().getMain().getTempMax()+"ºF");

        tvWindSpeed = (TextView) rootView.findViewById(R.id.tvWindSpeed);
        tvWindSpeed.setText("Wind Speed: "+((DetailsActivity)getActivity()).getWeatherResult().getWind().getSpeed()+" mph");

        String sunriseTime = new SimpleDateFormat("HH:mm")
                .format(new java.sql.Date(((DetailsActivity)getActivity()).getWeatherResult().getSys().getSunrise() * 1000L));

        tvSunrise = (TextView) rootView.findViewById(R.id.tvSunrise);
        tvSunrise.setText("Sunrise: "+sunriseTime+" A.M.");

        String sunsetTime = new SimpleDateFormat("hh:mm")
                .format(new java.sql.Date(((DetailsActivity)getActivity()).getWeatherResult().getSys().getSunset() * 1000L));

        tvSunset = (TextView) rootView.findViewById(R.id.tvSunset);
        tvSunset.setText("Sunset: "+sunsetTime+" P.M.");
//
        return rootView;
    }
}