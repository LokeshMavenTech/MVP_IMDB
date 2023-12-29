package network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL  = "https://api.themoviedb.org/";
    public static Retrofit retrofit=null;

    public static final String API_KEY = "9611ad116cf8e918fd77cfebc08a7c41";


    public static final String IMAGE_BASE_URL="https://image.tmdb.org/t/p/original/";

    public static  Retrofit getClient(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
