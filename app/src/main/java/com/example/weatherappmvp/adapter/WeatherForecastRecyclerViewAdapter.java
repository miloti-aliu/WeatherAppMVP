package com.example.weatherappmvp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappmvp.R;
import com.example.weatherappmvp.model.WeatherForecastModel;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherForecastRecyclerViewAdapter extends RecyclerView.Adapter<WeatherForecastRecyclerViewAdapter.ViewHolder> {

    private List<WeatherForecastModel.List> forecasts = new ArrayList<>();

    @NonNull
    @Override
    public WeatherForecastRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listForecasts = layoutInflater.inflate(R.layout.activity_city_forecast, parent, false);
        return new ViewHolder(listForecasts);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final WeatherForecastModel.List weatherForecastModel = forecasts.get(position);

        holder.fillView(weatherForecastModel);

    }

    public void addWeatherForecastModel(WeatherForecastModel model) {
        for (WeatherForecastModel.List list : model.getList()) {
            forecasts.add(list);
            notifyItemInserted(forecasts.size() - 1);
        }
    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView forecastIcon;
        TextView date;
        TextView minTemp;
        TextView maxTemp;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.forecastIcon = itemView.findViewById(R.id.icon);
            this.date = itemView.findViewById(R.id.date);
            this.minTemp = itemView.findViewById(R.id.minTemp);
            this.maxTemp = itemView.findViewById(R.id.maxTemp);
        }

        void fillView(WeatherForecastModel.List weatherForecastModel) {
            Picasso.get().load(new StringBuilder("https://openweathermap.org/img/w/").append(weatherForecastModel.getWeather().get(0).getIcon()).append(".png").toString()).into(forecastIcon);
            date.setText(getDay(weatherForecastModel.getDtTxt()) + ", " + getTime(weatherForecastModel.getDtTxt()));
            minTemp.setText(weatherForecastModel.getMain().getTempMin() + "°C");
            maxTemp.setText(weatherForecastModel.getMain().getTempMax() + "°C");
        }

        private String getDay(String dateTime) {
            String date = dateTime.split(" ")[0];
            Date date1 = null;
            try {
                date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return date1.toString().substring(0,10);
        }

        private String getTime(String dateTime) {
            String time = dateTime.split(" ")[1];
            return time.substring(0,5);
        }
    }
}
