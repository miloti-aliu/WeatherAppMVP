package com.example.weatherappmvp.presenter;

import android.content.Context;

import com.example.weatherappmvp.model.WeatherForecastModel;

public interface WeatherForecastInterface {

    interface View{
        void onGetDataSuccess(WeatherForecastModel model);
        void onGetDataFailure(String message);
    }

    interface Presenter{
        void getDataFromURL(Context context, String city);
    }

    interface InteractorInterface{
        void initRetrofitCall(Context context, String city);

    }

    interface onGetDataListener{
        void onSuccess(WeatherForecastModel model);
        void onFailure(String message);
    }
}
