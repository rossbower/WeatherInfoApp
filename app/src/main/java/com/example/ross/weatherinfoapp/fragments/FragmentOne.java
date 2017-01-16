package com.example.ross.weatherinfoapp.fragments;

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

public class FragmentOne extends Fragment {

    private TextView tvCurrentTemp;
    private TextView tvDescription;
    private ImageView ivLogo;
    private final String BASE = "http://openweathermap.org/img/w/";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_one, null);

        ((DetailsActivity) getActivity()).getSupportActionBar()
                .setTitle((""+((DetailsActivity)getActivity()).getWeatherResult().getName()));

        tvCurrentTemp = (TextView) rootView.findViewById(R.id.tvCurrentTemp);
        tvCurrentTemp.setText(""+((DetailsActivity)getActivity()).getWeatherResult().getMain().getTemp()+"ºF");

        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);
        tvDescription.setText(""+((DetailsActivity)getActivity()).getWeatherResult().getWeather().get(0).getMain());

        ivLogo = (ImageView) rootView.findViewById(R.id.ivLogo);
        Glide.with(this).load(BASE
                +((DetailsActivity)getActivity()).getWeatherResult().getWeather().get(0).getIcon()+".png")
                .into(ivLogo);
        return rootView;
    }
}