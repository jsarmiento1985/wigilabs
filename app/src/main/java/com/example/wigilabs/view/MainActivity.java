package com.example.wigilabs.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wigilabs.R;
import com.example.wigilabs.repository.local.controller.MovieController;
import com.example.wigilabs.repository.remote.retrofit.Comm.OnNotificationListener;
import com.example.wigilabs.repository.model.Pojo.Movie;
import com.example.wigilabs.repository.remote.retrofit.Servicios.MoviesClientRetrofit;
import com.example.wigilabs.adapters.MoviesAdapter;
import com.example.wigilabs.viewmodel.LiveDataMovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNotificationListener {

    private RecyclerView recyclerView;
    private Button service;
    private MoviesAdapter adapter;
    private List<Movie> movies;
    private TextView txtResponse;
    private MovieController movieController;
    private LiveDataMovieViewModel liveDataMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieController = new MovieController(this);

        setUpView();

        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MoviesClientRetrofit(MainActivity.this, MainActivity.this).execute();
            }
        });

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "seleccion: " + movies.get(recyclerView.getChildAdapterPosition(view)).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, MovieDetalle.class);
                intent.putExtra("objetoMovie", movies.get(recyclerView.getChildAdapterPosition(view)));
                startActivity(intent);
            }
        });

    }

    private void setUpView(){
        liveDataMovieViewModel = ViewModelProviders.of(this).get(LiveDataMovieViewModel.class);

        service = findViewById(R.id.btn_service);
        movies = new ArrayList<>();
        adapter = new MoviesAdapter(movies);
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager lim = new LinearLayoutManager(this);
        lim.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(lim);
        recyclerView.setAdapter(adapter);
        txtResponse = findViewById(R.id.txtResponse);
    }

    @Override
    public void showTitle(String title) {
        txtResponse.setText(title);
    }

    @Override
    public void showMessage(String msg) {
        txtResponse.setText(msg);
    }

    @Override
    public void showFinalDialog(String msg) {
        txtResponse.setText(msg);
        if(movieController.getMovies() != null){
            //sin viewmodel
            adapter.setData(movieController.getMovies());
            //conViewModel
            obtenerMovies();
            //llenar el array para saber que selecciono o click al recycler
            for(Movie items:movieController.getMovies()){
                movies.add(items);
            }
        }



    }

    @Override
    public void prepareProgress(int max) {

    }

    @Override
    public void showProgress(int max, int progress, boolean indeterminate) {

    }

    private void obtenerMovies(){
        final Observer<List<Movie>> listObserver = new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movies) {
                adapter.setData(movies);
            }
        };

        liveDataMovieViewModel.getMovieListObserver().observe(this,listObserver);
    }


}