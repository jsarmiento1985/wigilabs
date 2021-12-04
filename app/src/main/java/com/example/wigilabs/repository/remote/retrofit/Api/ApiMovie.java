package com.example.wigilabs.repository.remote.retrofit.Api;

import com.example.wigilabs.repository.model.Pojo.Response.DataMovies;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiMovie {

    @GET("popular?api_key=09963e300150f9831c46a1828a82a984&language=en-US")
    //Call<DataMovies> getMoviesAll(@Url String url);
    Call<DataMovies> getMoviesAll();

}
