package com.example.weatherappmvp.interactor;

import android.content.Context;
import android.util.Log;

import com.example.weatherappmvp.model.WeatherModel;
import com.example.weatherappmvp.presenter.WeatherInterface;
import com.example.weatherappmvp.retrofit.RetrofitClient;
import com.example.weatherappmvp.retrofit.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInteractor implements WeatherInterface.InteractorInterface {

    private WeatherInterface.onGetDataListener mOnGetDataListener;
    WeatherService weatherService = RetrofitClient.getRetrofitInstance().create(WeatherService.class);

    public WeatherInteractor(WeatherInterface.onGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
    }


    @Override
    public void initRetrofitCall(Context context) {
        Call<WeatherModel> call =
                weatherService.getWeather("524901,703448,2643743,658226,3183875,2673730,5128581", "metric", "26fbb994d78d012c388d5ecb2f45f701");

        call.enqueue(new Callback<WeatherModel>() {
            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                mOnGetDataListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.v("Error", t.getMessage());
            }
        });
    }
}
