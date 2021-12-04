package com.example.wigilabs.repository.model.Pojo.Response;

import com.example.wigilabs.repository.model.Pojo.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataMovies {
    @SerializedName("results")
    private List<Movie> listMovies;

    public List<Movie> getListMovies() {
        return listMovies;
    }

    public void setListMovies(List<Movie> listMovies) {
        this.listMovies = listMovies;
    }
}
