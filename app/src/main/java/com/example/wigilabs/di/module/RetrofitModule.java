package com.example.wigilabs.di.module;

import com.example.wigilabs.repository.remote.retrofit.Api.ApiMovie;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private static final String
             //BASE_URL = "https://api.themoviedb.org/3/movie/popular?api_key=09963e300150f9831c46a1828a82a984&language=en-US";
            BASE_URL = "https://api.themoviedb.org/3/movie/";

    @Singleton
    @Provides
    GsonConverterFactory provideGsonConverterFactory (){
        return GsonConverterFactory.create();
    }

    @Singleton
    @Provides
    HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Singleton
    @Provides
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor){
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit (OkHttpClient client, GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(gsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    ApiMovie providesApiPersona(Retrofit retrofit){
        return retrofit.create(ApiMovie.class);
    }

}
