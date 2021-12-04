package com.example.wigilabs.repository.local.db;

import android.provider.BaseColumns;

/**
 * Estructura de base de datos local
 */
public final class DbContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INTEGER_TYPE = " INTEGER";
    public static final String REAL_TYPE = " REAL";
    public static final String BLOB_TYPE = " BLOB";
    public static final String PRIMARY_KEY = " PRIMARY KEY";
    public static final String DEFAULT = " DEFAULT ";
    public static final String COMMA_SEP = ",";

    public DbContract() {}

    // :::: INICIO TABLAS BASE ::::

    public static abstract class MoviesTable implements BaseColumns {
        public static final String TABLE_NAME = "MOVIES";
        public static final String COL_ID= "ID";
        public static final String COL_TITULO = "TITULO";
        public static final String COL_DESCRIPCION = "DESCRIPCION";
        public static final String COL_IDIOMA = "IDIOMA";
        public static final String COL_IMAGEN = "IMAGEN";
        public static final String COL_VIDEO = "VIDEO";
        public static final String COL_VOTACION_AVG = "VOTACION_AVG";
        public static final String COL_FECHA_RELEASE = "FECHA_RELEASE";
        public static final String COL_POPULARIDAD = "POPULARIDAD";

        static final String SQL_CREATE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        COL_ID + INTEGER_TYPE + COMMA_SEP +
                        COL_IDIOMA + TEXT_TYPE + COMMA_SEP +
                        COL_TITULO + TEXT_TYPE +  COMMA_SEP +
                        COL_VIDEO + INTEGER_TYPE + COMMA_SEP +
                        COL_DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                        COL_IMAGEN + TEXT_TYPE + COMMA_SEP +
                        COL_VOTACION_AVG + REAL_TYPE + COMMA_SEP +
                        COL_FECHA_RELEASE + TEXT_TYPE + COMMA_SEP +
                        COL_POPULARIDAD + REAL_TYPE + COMMA_SEP +

                        PRIMARY_KEY + " (" + COL_ID + "))";
    }






}
