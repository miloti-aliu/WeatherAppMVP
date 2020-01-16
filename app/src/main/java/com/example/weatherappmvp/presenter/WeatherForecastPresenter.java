package com.example.weatherappmvp.presenter;

import android.content.Context;

import com.example.weatherappmvp.interactor.WeatherForecastInteractor;
import com.example.weatherappmvp.model.WeatherForecastModel;

public class WeatherForecastPresenter implements WeatherForecastInterface.Presenter, WeatherForecastInterface.onGetDataListener{

    private WeatherForecastInterface.View mGetDataViev;
    private WeatherForecastInteractor mInteractor;

    public WeatherForecastPresenter(WeatherForecastInterface.View v){
        mGetDataViev = v;
        mInteractor = new WeatherForecastInteractor(this);
    }

    @Override
    public void onSuccess(WeatherForecastModel model) {
        mGetDataViev.onGetDataSuccess(model);
    }

    @Override
    public void onFailure(String message) {
        mGetDataViev.onGetDataFailure(message);
    }

    @Override
    public void getDataFromURL(Context context, String city) {
        mInteractor.initRetrofitCall(context, city);
    }

}
