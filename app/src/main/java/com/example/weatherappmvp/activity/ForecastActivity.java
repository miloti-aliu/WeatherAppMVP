package com.example.weatherappmvp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherappmvp.R;
import com.example.weatherappmvp.adapter.WeatherForecastRecyclerViewAdapter;
import com.example.weatherappmvp.model.WeatherForecastModel;
import com.example.weatherappmvp.presenter.WeatherForecastInterface;
import com.example.weatherappmvp.presenter.WeatherForecastPresenter;
import com.example.weatherappmvp.retrofit.RetrofitClient;
import com.example.weatherappmvp.retrofit.WeatherService;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForecastActivity extends AppCompatActivity implements WeatherForecastInterface.View {

    @BindView(R.id.rvForecasts) RecyclerView recyclerView;

    private WeatherForecastPresenter weatherForecastPresenter;
    private WeatherForecastRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String city = intent.getStringExtra("cityName");

        bindAdapter();

        weatherForecastPresenter = new WeatherForecastPresenter(this);
        weatherForecastPresenter.getDataFromURL(getApplicationContext(), city);
    }

    private void bindAdapter() {
        mAdapter = new WeatherForecastRecyclerViewAdapter();
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
