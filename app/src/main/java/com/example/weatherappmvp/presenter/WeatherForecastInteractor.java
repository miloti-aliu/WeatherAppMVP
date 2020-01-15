package com.example.weatherappmvp.presenter;

import android.content.Context;
import android.util.Log;

import com.example.weatherappmvp.model.WeatherForecastModel;
import com.example.weatherappmvp.retrofit.RetrofitClient;
import com.example.weatherappmvp.retrofit.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastInteractor implements WeatherForecastInterface.InteractorInterface {

    private WeatherForecastInterface.onGetDataListener mOnGetDataListener;
    WeatherService weatherService = RetrofitClient.getRetrofitInstance().create(WeatherService.class);

    public WeatherForecastInteractor(WeatherForecastInterface.onGetDataListener onGetDataListener) {
        mOnGetDataListener = onGetDataListener;
    }


    @Override
    public void initRetrofitCall(Context context, String city) {
        Call<WeatherForecastModel> call =
                weatherService.getWeatherForecast(city, "metric", "26fbb994d78d012c388d5ecb2f45f701");

        call.enqueue(new Callback<WeatherForecastModel>() {
            @Override
            public void onResponse(Call<WeatherForecastModel> call, Response<WeatherForecastModel> response) {
                mOnGetDataListener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<WeatherForecastModel> call, Throwable t) {
                Log.v("Error", t.getMessage());
            }
        });
    }
}
