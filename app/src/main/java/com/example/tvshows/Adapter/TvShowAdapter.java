package com.example.tvshows.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshows.R;
import com.example.tvshows.Model.TvShow;
import com.example.tvshows.databinding.MovieItemBinding;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    List<TvShow> showsList;
    Context context;
    public TvShowAdapter(Context context, ArrayList<TvShow> list)
    {
        this.context = context;
        this.showsList = list;
    }

     static class TvShowViewHolder extends RecyclerView.ViewHolder {
        MovieItemBinding binding;
        public TvShowViewHolder(@NonNull  View itemView) {
            super(itemView);
            binding = MovieItemBinding.bind(itemView);
        }
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        TvShow tvShow = showsList.get(position);
        holder.binding.tvTitle.setText(tvShow.getTitle());
        holder.binding.tvRating.setText(tvShow.getVote());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+tvShow.getPoster_path()).into(holder.binding.ivPoster);
    }


    @Override
    public int getItemCount() {
        return showsList.size();
    }
}
