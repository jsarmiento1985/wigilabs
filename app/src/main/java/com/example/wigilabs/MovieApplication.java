package com.example.wigilabs;


import android.app.Application;

import com.example.wigilabs.di.component.DaggerRetrofitComponent;
import com.example.wigilabs.di.component.RetrofitComponent;
import com.example.wigilabs.di.module.RetrofitModule;

public class MovieApplication extends Application {
    private RetrofitComponent retrofitComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofitComponent = DaggerRetrofitComponent
                .builder()
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public RetrofitComponent getRetrofitComponent() {
        return retrofitComponent;
    }
}
