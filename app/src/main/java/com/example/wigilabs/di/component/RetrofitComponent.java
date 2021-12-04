package com.example.wigilabs.di.component;

import com.example.wigilabs.repository.remote.retrofit.Servicios.MoviesClientRetrofit;
import com.example.wigilabs.di.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RetrofitModule.class)
public interface RetrofitComponent {
    void inject(MoviesClientRetrofit moviesClientRetrofit);
}
