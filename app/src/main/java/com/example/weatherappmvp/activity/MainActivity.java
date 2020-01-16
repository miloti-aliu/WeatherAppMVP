package com.example.weatherappmvp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.weatherappmvp.R;
import com.example.weatherappmvp.adapter.WeatherRecyclerViewAdapter;
import com.example.weatherappmvp.model.WeatherModel;
import com.example.weatherappmvp.presenter.WeatherInterface;
import com.example.weatherappmvp.presenter.WeatherPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherInterface.View {

    @BindView(R.id.rvCities)RecyclerView recyclerView;

    private WeatherPresenter weatherPresenter = new WeatherPresenter(this);
    private WeatherRecyclerViewAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        bindAdapter();

        weatherPresenter.getDataFromURL(getApplicationContext());

    }

    private void bindAdapter() {
        mAdapter = new WeatherRecyclerViewAdapter();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onGetDataSuccess(WeatherModel model) {
        mAdapter.addWeatherModel(model);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }
}
