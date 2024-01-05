package view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import app.scrollfrom.mvp_imdb.R;
import model.Movie;
import network.ApiClient;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {

    private List<Movie> movieList;
    private Context context;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isTopRated;
    public MovieListAdapter(List<Movie> movieList, Context context, RecyclerView.LayoutManager layoutManager, boolean isTopRated) {
        this.movieList = movieList;
        this.context = context;
        this.layoutManager = layoutManager;
        this.isTopRated = isTopRated;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_itam_movie_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        holder.tvMovieTitel.setText(movie.getTitle());
        holder.tvMovieReleseDate.setText(movie.getReleaseDate());
        holder.tvMovieOverView.setText(movie.getOverview());

        String imageUrl = ApiClient.IMAGE_BASE_URL + movie.getBackdropPath();
        Glide.with(context).load(imageUrl).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return movieList.size();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvMovieTitel,tvMovieReleseDate,tvMovieOverView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMovie=itemView.findViewById(R.id.ivMovie);
            tvMovieTitel = itemView.findViewById(R.id.tvTitelMovie);
            tvMovieReleseDate =itemView.findViewById(R.id.tvReleseDateMovie);
            tvMovieOverView = itemView.findViewById(R.id.tvOverviewDateMovie);

        }

    }
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(layoutManager);

    }
    public boolean isTopRated() {
        return isTopRated;
    }
}
