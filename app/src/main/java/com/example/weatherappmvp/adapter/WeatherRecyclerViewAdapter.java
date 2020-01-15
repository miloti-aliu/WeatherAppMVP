package com.example.weatherappmvp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappmvp.R;
import com.example.weatherappmvp.activity.ForecastActivity;
import com.example.weatherappmvp.model.WeatherModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WeatherRecyclerViewAdapter extends RecyclerView.Adapter<WeatherRecyclerViewAdapter.ViewHolder> {

    private List<WeatherModel.List> weatherList = new ArrayList<>();

    @NonNull
    @Override
    public WeatherRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listCity = layoutInflater.inflate(R.layout.activity_item_city, parent, false);
        return new ViewHolder(listCity);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final WeatherModel.List weather = weatherList.get(position);

        holder.fillView(weather);

        holder.linearLayout.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ForecastActivity.class);
            intent.putExtra("cityName", weather.getName());
            v.getContext().startActivity(intent);
        });
    }

    public void addWeatherModel(WeatherModel model){
        for (WeatherModel.List list:model.getList()) {
            weatherList.add(list);
            notifyItemInserted(weatherList.size()-1);
        }
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView weatherIcon;
        TextView cityName;
        TextView weatherTemp;
        LinearLayout linearLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.weatherIcon = itemView.findViewById(R.id.weatherIcon);
            this.cityName = itemView.findViewById(R.id.cityName);
            this.weatherTemp = itemView.findViewById(R.id.weatherTemp);
            this.linearLayout = itemView.findViewById(R.id.mainLayout);
        }

        void fillView(WeatherModel.List weather) {
            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weather.getWeather().get(0).getIcon()).append(".png").toString()).into(weatherIcon);
            cityName.setText(weather.getName());
            weatherTemp.setText(weather.getMain().getTemp()+"°C");
        }
    }
}
