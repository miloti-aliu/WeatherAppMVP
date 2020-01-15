package com.example.weatherappmvp.presenter;

import android.content.Context;

import com.example.weatherappmvp.model.WeatherModel;

public interface WeatherInterface {
    interface View{
        void onGetDataSuccess(WeatherModel model);
        void onGetDataFailure(String message);
    }

    interface Presenter{
        void getDataFromURL(Context context);
    }

    interface InteractorInterface{
        void initRetrofitCall(Context context);

    }

    interface onGetDataListener{
        void onSuccess(WeatherModel model);
        void onFailure(String message);
    }
}
