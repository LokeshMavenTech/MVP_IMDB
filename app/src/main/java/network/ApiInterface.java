package network;

import model.MovieListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("3/movie/popular")
    Call<MovieListResponse> getPopularMovies(
            @Query("api_key")String api_key,
            @Query("page") int pageNo
    );
}
