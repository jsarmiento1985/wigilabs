package com.example.wigilabs;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.module.AppGlideModule;
import com.example.wigilabs.retrofit.Pojo.Movie;

import java.util.ArrayList;

public class MovieDetalle extends AppCompatActivity {

    private ImageView ivImagen;
    private TextView tvTitulo, tvDescripcion, tvVotacion, tvFecha, tvPopularidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detalle);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        ivImagen = findViewById(R.id.iv_imagen);
        tvTitulo = findViewById(R.id.tv_titulo);
        tvDescripcion = findViewById(R.id.tv_descripcion);
        tvVotacion = findViewById(R.id.tv_votacion);
        tvFecha = findViewById(R.id.tv_fecha);
        tvPopularidad = findViewById(R.id.tv_popularidad);

        Movie movie = (Movie) getIntent().getSerializableExtra("objetoMovie");

        Glide.with(getApplicationContext())
                .load("https://www.lavanguardia.com/files/image_449_220/uploads/2019/12/20/5fa53034cd854.jpeg")
                //.load(movie.getBackdropPath())
                .override(500, 500)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(ivImagen);

        tvTitulo.setText(movie.getTitle());
        tvDescripcion.setText(movie.getOverview());
        tvFecha.setText(movie.getReleaseDate());
        tvVotacion.setText(String.valueOf(movie.getVoteAverage()));
        double valor = movie.getPopularity() > 0 ?  movie.getPopularity(): 0.0;
        tvPopularidad.setText( String.valueOf(valor) );


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}