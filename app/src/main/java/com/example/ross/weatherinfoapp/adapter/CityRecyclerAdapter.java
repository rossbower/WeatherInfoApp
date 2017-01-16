package com.example.ross.weatherinfoapp.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ross.weatherinfoapp.DetailsActivity;
import com.example.ross.weatherinfoapp.EditInterface;
import com.example.ross.weatherinfoapp.MainActivity;
import com.example.ross.weatherinfoapp.R;
import com.example.ross.weatherinfoapp.data.city.City;

import java.util.List;

public class CityRecyclerAdapter extends
        RecyclerView.Adapter<CityRecyclerAdapter.ViewHolder>
        implements CityTouchHelperAdapter {

    private List<City> cityList;
    private EditInterface editInterface;

    public CityRecyclerAdapter(EditInterface editInterface) {
        this.editInterface = editInterface;

        cityList = City.listAll(City.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View cityRow = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.city_row,parent,false);
        return new ViewHolder(cityRow);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvCity.setText(cityList.get(position).getCityName());

        holder.icDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityList.get(position).delete();
                cityList.remove(position);
                notifyItemRemoved(position);
            }
        });

        holder.viewRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(holder.viewRow.getContext(), DetailsActivity.class);
                intent.putExtra(MainActivity.CITY_NAME, holder.tvCity.getText());
                holder.viewRow.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    @Override
    public void onItemDismiss(int position) {
        cityList.get(position).delete();

        cityList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        cityList.add(toPosition, cityList.get(fromPosition));
        cityList.remove(fromPosition);

        notifyItemMoved(fromPosition, toPosition);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCity;
        private ImageView icDelete;
        private View viewRow;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            viewRow = (View) itemView.findViewById(R.id.row);
            icDelete = (ImageView) itemView.findViewById(R.id.icDelete);
        }
    }

    public void addCity(City city) {


        city.save();
        cityList.add(0, city);

        notifyItemInserted(0);
    }

    public void edit(City city, int position) {
        city.save();
        cityList.set(position, city);
        notifyItemChanged(position);
    }

}