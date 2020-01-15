package com.example.weatherappmvp.presenter;

import android.content.Context;

import com.example.weatherappmvp.model.WeatherModel;

public class WeatherPresenter implements WeatherInterface.Presenter, WeatherInterface.onGetDataListener {

    private WeatherInterface.View mGetDataViev;
    private WeatherInteractor mInteractor;

    public WeatherPresenter(WeatherInterface.View v) {
        mGetDataViev = v;
        mInteractor = new WeatherInteractor(this);
    }

    @Override
    public void onSuccess(WeatherModel model) {
        mGetDataViev.onGetDataSuccess(model);
    }

    @Override
    public void onFailure(String message) {
        mGetDataViev.onGetDataFailure(message);
    }

    @Override
    public void getDataFromURL(Context context) {
        mInteractor.initRetrofitCall(context);
    }
}
