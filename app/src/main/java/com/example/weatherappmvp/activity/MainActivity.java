package com.example.weatherappmvp.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

    @BindView(R.id.rvCities)
    RecyclerView recyclerView;

    private WeatherPresenter weatherPresenter;
    private WeatherRecyclerViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        attachPresenter();
    }

    private void attachPresenter() {
        mAdapter = (WeatherRecyclerViewAdapter) getLastCustomNonConfigurationInstance();
        if (mAdapter == null) {
            bindAdapter();
            weatherPresenter = new WeatherPresenter(this);
            weatherPresenter.getDataFromURL(getApplicationContext());
        } else {
            bindRecyclerView();
        }
    }

    @Nullable
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return (mAdapter);
    }

    private void bindAdapter() {
        mAdapter = new WeatherRecyclerViewAdapter();
        bindRecyclerView();
    }

    private void bindRecyclerView(){
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
