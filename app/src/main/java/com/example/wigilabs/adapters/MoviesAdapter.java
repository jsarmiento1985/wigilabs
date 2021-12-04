package com.example.wigilabs.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wigilabs.R;
import com.example.wigilabs.repository.model.Pojo.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterHolder> implements View.OnClickListener {

    private List<Movie> movies;
    private View.OnClickListener listener;
    Context ctx;
    public MoviesAdapter(List<Movie> movies){
        this.movies = movies;
    }


    @NonNull
    @Override
    public MoviesAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_movies, parent, false);
        itemView.setOnClickListener(this);
        ctx = parent.getContext();

        return new MoviesAdapterHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitulo.setText(movie.getTitle());
        holder.tvDescripcion.setText(movie.getOverview());
        holder.tvIdioma.setText(movie.getOriginalLanguage());
        Glide.with(ctx)
                .load("https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/mejores-peliculas-historia-cine-1617955822.jpeg?crop=0.502xw:1.00xh;0.348xw,0&resize=640:*")
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.ivImagenMini);

    }


    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setData(List<Movie> movie){
        this.movies = movie;
        notifyDataSetChanged();
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }

    }


    public class MoviesAdapterHolder extends RecyclerView.ViewHolder{
        private TextView tvTitulo;
        private TextView tvIdioma;
        private TextView tvDescripcion;
        private ImageView ivImagenMini;

        public MoviesAdapterHolder (@NonNull View itemView){
            super(itemView);
            ivImagenMini = itemView.findViewById(R.id.ivImagenMini);
            tvTitulo = itemView.findViewById(R.id.tvTituloDown);
            tvIdioma = itemView.findViewById(R.id.tvIdiomaDown);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionDown);

        }

    }





}
