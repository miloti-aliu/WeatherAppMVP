package com.example.weatherappmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappmvp.R;
import com.example.weatherappmvp.adapter.WeatherForecastRecyclerViewAdapter;
import com.example.weatherappmvp.adapter.WeatherRecyclerViewAdapter;
import com.example.weatherappmvp.model.WeatherForecastModel;
import com.example.weatherappmvp.presenter.WeatherForecastInterface;
import com.example.weatherappmvp.presenter.WeatherForecastPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForecastActivity extends AppCompatActivity implements WeatherForecastInterface.View {

    @BindView(R.id.rvForecasts)
    RecyclerView recyclerView;

    private WeatherForecastPresenter weatherForecastPresenter;
    private WeatherForecastRecyclerViewAdapter mAdapter;
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        attachPresnter();
        /*Intent intent = getIntent();
        city = intent.getStringExtra("cityName");

        bindAdapter();

        weatherForecastPresenter = new WeatherForecastPresenter(this);
        weatherForecastPresenter.getDataFromURL(getApplicationContext(), city);*/
    }

    private void attachPresnter(){
        mAdapter = (WeatherForecastRecyclerViewAdapter) getLastCustomNonConfigurationInstance();
        if(mAdapter == null){
            Intent intent = getIntent();
            city = intent.getStringExtra("cityName");
            bindAdapter();
            weatherForecastPresenter = new WeatherForecastPresenter(this);
            weatherForecastPresenter.getDataFromURL(getApplicationContext(), city);
        }
        else {
            bindRecyclerView();
        }
    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return (mAdapter);
    }

    private void bindAdapter() {
        mAdapter = new WeatherForecastRecyclerViewAdapter();
        bindRecyclerView();
    }

    private void bindRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGetDataSuccess(WeatherForecastModel model) {
        mAdapter.addWeatherForecastModel(model);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
