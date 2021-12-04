package com.example.wigilabs.repository.local;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wigilabs.repository.local.controller.MovieController;
import com.example.wigilabs.repository.model.Pojo.Movie;

import java.util.List;

public class MovieRepository  {

    Context contex;
    private MovieController movieController;

    public MovieRepository(Context contexto) {
        this.contex = contexto;
        movieController = new MovieController(contex);
    }

    public MutableLiveData<List<Movie>> getMoviesRepository(){
        final MutableLiveData<List<Movie>> data = new MutableLiveData<List<Movie>>();
         List <Movie> movieList;

        movieList = movieController.getMovies();
        data.setValue(movieList);

        return data;
    }

}
