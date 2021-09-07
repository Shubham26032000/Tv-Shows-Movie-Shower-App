package com.example.tvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tvshows.Adapter.TrendingAdapter;
import com.example.tvshows.Adapter.TvShowAdapter;
import com.example.tvshows.Model.TvShow;
import com.example.tvshows.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TvShowAdapter tvShowAdapter;
    ArrayList<TvShow> tvShows;
    TrendingAdapter trendingAdapter;
    ArrayList<TvShow> trendingShows;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        //Popular Tv Show
        tvShows = new ArrayList<>();
        getPopularTvShows();
        tvShowAdapter = new TvShowAdapter(getApplicationContext(),tvShows);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        binding.showList.setHasFixedSize(true);
        binding.showList.setLayoutManager(layoutManager);
        binding.showList.setAdapter(tvShowAdapter);

        //Trending Movie & Shows
        trendingShows = new ArrayList<>();
        getTrendingShows();
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getApplicationContext());
        layoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        binding.trendingShows.setHasFixedSize(true);
        trendingAdapter = new TrendingAdapter(getApplicationContext(),trendingShows);
        binding.trendingShows.setLayoutManager(layoutManager1);
        binding.trendingShows.setAdapter(trendingAdapter);

    }

    /***
     * This Method fetch popular tv shows
     */
    private void getPopularTvShows() {
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=b4bb39a87e46e74cc25ef8f48d99a511";
        StringRequest request =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    tvShows.addAll(setShowData(response));
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Ops ! Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }

    /***
     * This Method fetch trending tv shows & Movies
     */
    private void getTrendingShows()
    {
        String Url = "https://api.themoviedb.org/3/trending/tv/week?api_key=b4bb39a87e46e74cc25ef8f48d99a511";
        StringRequest request =new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    trendingShows.addAll(setShowData(response));
                }catch (Exception e)
                {
                    Toast.makeText(MainActivity.this, "Ops ! Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);
    }


    /**
     * This Methods return desired item.
     * @param response
     * @return
     * @throws JSONException
     */
    private ArrayList<TvShow> setShowData(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray   = jsonObject.getJSONArray("results");
        ArrayList<TvShow> list = new ArrayList<>();
        for (int i=0; i<jsonArray.length(); i++)
        {
            TvShow tvShow = new TvShow();
            tvShow.setTitle(jsonArray.getJSONObject(i).getString("original_name"));
            tvShow.setPoster_path(jsonArray.getJSONObject(i).getString("poster_path"));
            tvShow.setVote(jsonArray.getJSONObject(i).getString("vote_average"));
            list.add(tvShow);
        }
        return list;
    }


}