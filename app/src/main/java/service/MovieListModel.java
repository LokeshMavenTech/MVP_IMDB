package service;

import android.util.Log;

import java.util.List;

import contract.MovieListContact;
import model.Movie;
import model.MovieListResponse;
import network.ApiClient;
import network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListModel implements MovieListContact.Model {

    private  final String TAG="MovieListModel";
    private int pageNo= 1;
    @Override
    public void getMovieList(onFinishedListener onFinishedListener, int pageNo) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MovieListResponse> call= apiService.getPopularMovies(ApiClient.API_KEY,pageNo);
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.e(TAG,"Number of Our Movie Receved:"+movies.size());
                onFinishedListener.onFinished(movies);
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }
}
