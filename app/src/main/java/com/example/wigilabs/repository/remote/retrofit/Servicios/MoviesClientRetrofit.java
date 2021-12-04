package com.example.wigilabs.repository.remote.retrofit.Servicios;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wigilabs.MovieApplication;
import com.example.wigilabs.repository.local.controller.MovieController;
import com.example.wigilabs.repository.local.db.Transaction;
import com.example.wigilabs.repository.remote.retrofit.Api.ApiMovie;
import com.example.wigilabs.repository.remote.retrofit.Comm.OnNotificationListener;
import com.example.wigilabs.repository.model.Pojo.Movie;
import com.example.wigilabs.repository.model.Pojo.Response.DataMovies;


import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;

import static com.example.wigilabs.repository.local.db.Transaction.INSERT;
import static com.example.wigilabs.repository.local.db.Transaction.UPDATE;

public class MoviesClientRetrofit extends AsyncTask<String, String, Void> {
    public final static String TAG = "MoviesService";
    private int cantRegistros;
    private Context context;
    private OnNotificationListener listener;
    private  int cont;

    private MovieController movieController;
    @Inject
    ApiMovie client;

    public MoviesClientRetrofit(Context context, OnNotificationListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @Override
    protected void onPreExecute() {
        listener.showTitle("Movies");
        setUpDagger();
    }


    @Override
    protected Void doInBackground(String... strings) {

        movieController = new MovieController(context);

        cantRegistros=1;
        publishProgress("0", "Conectando servicio");
        //Call<DataMovies> call =client.getMoviesAll("https://api.themoviedb.org/3/movie/popular?api_key=09963e300150f9831c46a1828a82a984&language=en-US");
        Call<DataMovies> call =client.getMoviesAll();
        try {
            DataMovies data = call.execute().body();

            if(data != null){
                publishProgress("4","0");
                cantRegistros = data.getListMovies().size();
                almacenandoData(cantRegistros,data);
            }

        } catch (IOException e) {
            Log.d("retrofitError","Error: " + e.getMessage());
            publishProgress("0", "Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        switch (values[0]) {
            case "0":
                listener.showMessage(values[1]);
                Log.e(TAG, values[1]);
                break;
            case "1":
                listener.showMessage(values[1]);
                // Log.i(TAG, values[1]);
                break;
            case "3":
                listener.showFinalDialog(values[1]);//finalizar base de datos
                Log.e(TAG, values[1]);
                break;
            case "4":
                listener.prepareProgress(Integer.parseInt(values[1]));
                break;
            case "5":
                listener.showProgress(Integer.parseInt(values[1]), Integer.parseInt(values[2]),
                        Boolean.parseBoolean(values[3]));
        }
    }


    private void almacenandoData(int cantRegistros,  DataMovies data){
        publishProgress("1", "Procesando "+ cantRegistros +(cantRegistros > 1 ? " movie":" movies") );
        if(data != null){

            int strId = 0;
            String strIdioma  = "";
            String strTitulo= "";
            int   strVideo = 0;
            String strDescripcion = "";
            String strImagen = "";
            Double strVotacionAvg = 0.0 ;
            String strFechaRelease = "";
            Double strPopularidad= 0.0 ;

            for(Movie movie:data.getListMovies()){
                cont= cont+1;
                publishProgress("5",String.valueOf(cantRegistros) ,String.valueOf(cont),"false" );

                strId = movie.getId();
                strIdioma = movie.getOriginalLanguage();
                strTitulo = movie.getTitle();
                strVideo = movie.isVideo()? 1 : 0;
                strDescripcion = movie.getOverview();
                strImagen = movie.getPosterPath();
                strVotacionAvg = movie.getVoteAverage();
                strFechaRelease = movie.getReleaseDate();
                strPopularidad = movie.getPopularity();

                Transaction transaction;
                if (movieController.existMovie(strId)) {
                    Log.d(TAG, "Actualizando  ID: " + strId);
                    publishProgress("1", "Actualizando: ");
                    transaction = UPDATE;
                } else {
                    Log.d(TAG, "Guardando ID: " + strId);
                    publishProgress("1", "Guardando: " + strId);
                    transaction = INSERT;
                }

                movieController.putMovie(transaction, strId, strIdioma, strTitulo, strVideo, strDescripcion, strImagen, strVotacionAvg, strFechaRelease, strPopularidad );
            }

            publishProgress("3", "Service Finalizado");


        }


    }

    private void setUpDagger() {
        ((MovieApplication)context.getApplicationContext()).getRetrofitComponent().inject(this);
    }

}
