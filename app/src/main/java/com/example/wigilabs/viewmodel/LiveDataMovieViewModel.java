package com.example.wigilabs.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.wigilabs.repository.local.MovieRepository;

import com.example.wigilabs.repository.model.Pojo.Movie;
import java.util.List;

public class LiveDataMovieViewModel extends ViewModel {
    private MutableLiveData<List<Movie>> moviesListLiveData;
    private MovieRepository movieRepository;

    public LiveDataMovieViewModel(Context ctx) {
        movieRepository = new MovieRepository(ctx);
       this.moviesListLiveData =  movieRepository.getMoviesRepository();
    }

    public LiveData<List<Movie>> getMovieListObserver(){
        if(moviesListLiveData == null){
            moviesListLiveData = new MutableLiveData<List<Movie>>();
        }

        return moviesListLiveData;
    }

}
