package com.example.wigilabs.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.wigilabs.repository.local.db.DbContract;
import com.example.wigilabs.repository.local.db.Transaction;
import com.example.wigilabs.repository.model.Pojo.Movie;

import java.util.ArrayList;

public class MovieController extends Controller  {

    public MovieController(Context context) {
        super(context);
    }


    public boolean existMovie(int id) {
        Cursor cursor = db.query(DbContract.MoviesTable.TABLE_NAME,
                null, DbContract.MoviesTable.COL_ID   + "=" + id,
               null, null, null, null);

        boolean exist = cursor.moveToFirst();
        cursor.close();
        return exist;
    }

    public void putMovie(Transaction transaction, int strId, String strIdioma,
                           String strTitulo, int strVideo, String strDescripcion,
                           String strImagen, double strVotacionAvg, String strFechaRelease, double strPopularidad) {



        ContentValues valores = new ContentValues();
        valores.put(DbContract.MoviesTable.COL_ID, strId);
        valores.put(DbContract.MoviesTable.COL_IDIOMA, strIdioma);
        valores.put(DbContract.MoviesTable.COL_TITULO, strTitulo);
        valores.put(DbContract.MoviesTable.COL_VIDEO, strVideo);
        valores.put(DbContract.MoviesTable.COL_DESCRIPCION, strDescripcion);
        valores.put(DbContract.MoviesTable.COL_IMAGEN, strImagen);
        valores.put(DbContract.MoviesTable.COL_VOTACION_AVG, strVotacionAvg);
        valores.put(DbContract.MoviesTable.COL_FECHA_RELEASE, strFechaRelease);
        valores.put(DbContract.MoviesTable.COL_POPULARIDAD, strPopularidad);



        if (transaction == Transaction.INSERT)
            db.insert(DbContract.MoviesTable.TABLE_NAME, null, valores);
        else if(transaction == Transaction.UPDATE)
            db.update(DbContract.MoviesTable.TABLE_NAME, valores,
                    DbContract.MoviesTable.COL_ID  + "=" + strId, null);

    }

    public ArrayList<Movie> getMovies() {
        ArrayList<Movie> ListMovies = new ArrayList<>();

        String col[] = {
                DbContract.MoviesTable.COL_ID,            // 0
                DbContract.MoviesTable.COL_TITULO,      // 1
                DbContract.MoviesTable.COL_DESCRIPCION,   // 2
                DbContract.MoviesTable.COL_IDIOMA,          // 3
                DbContract.MoviesTable.COL_IMAGEN,      // 4
                DbContract.MoviesTable.COL_VIDEO,   // 5
                DbContract.MoviesTable.COL_FECHA_RELEASE,   // 6
                DbContract.MoviesTable.COL_POPULARIDAD,   // 7
                DbContract.MoviesTable.COL_VOTACION_AVG   // 8

        };


        Cursor cursor = db.query(DbContract.MoviesTable.TABLE_NAME, col,
                null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Movie pelicula = new Movie();
                pelicula.setId(cursor.getInt(0));
                pelicula.setTitle(cursor.getString(1));
                pelicula.setOverview(cursor.getString(2));
                pelicula.setOriginalLanguage(cursor.getString(3));
                pelicula.setPosterPath(cursor.getString(4));
                pelicula.setVideo(cursor.getInt(5) == 1 ? true : false);
                pelicula.setReleaseDate(cursor.getString(6));
                pelicula.setPopularity(cursor.getInt(7));
                pelicula.setVoteAverage(cursor.getInt(8));


                ListMovies.add(pelicula);

            } while (cursor.moveToNext());
        }

        cursor.close();

        return ListMovies;
    }


}
