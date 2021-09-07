package com.example.tvshows.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tvshows.R;
import com.example.tvshows.Model.TvShow;
import com.example.tvshows.databinding.TrendingShowsItemBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder> {
    List<TvShow> showsList;
    Context context;

    public TrendingAdapter(Context context, ArrayList<TvShow> list)
    {
        this.context = context;
        showsList = list;
    }

    @NonNull
    @Override
    public TrendingViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.trending_shows_item,parent,false);
        return new TrendingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrendingAdapter.TrendingViewHolder holder, int position) {
        TvShow tvShow = showsList.get(position);
        holder.binding.tvTitle.setText(tvShow.getTitle());
        holder.binding.tvRating.setText(tvShow.getVote());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+tvShow.getPoster_path()).into(holder.binding.ivPoster);
    }

    @Override
    public int getItemCount() {
        return showsList.size();
    }

    static class TrendingViewHolder extends RecyclerView.ViewHolder {
        TrendingShowsItemBinding binding;
        public TrendingViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = TrendingShowsItemBinding.bind(itemView);
        }
    }
}
