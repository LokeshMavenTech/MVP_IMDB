package network;

import model.MovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular")
    Call<MovieListResponse> getPopularMovies(
            @Query("api_key")String api_key,
            @Query("page") int pageNo
    );

    @GET("3/movie/top_rated")
    Call<MovieListResponse> getTopRated(
            @Query("api_key")String api_key,
            @Query("page") int page
    );



    @GET("3/movie/{movie_id}?")
    Call<MovieListResponse> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key")String api_key
    );


//    Call<MovieSearchResponse> searchMovie(String apiKey, String action, String number);
}
