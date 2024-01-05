package app.scrollfrom.mvp_imdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import contract.MovieListContact;
import model.Movie;
import presenter.MoviePresenter;
import view.MovieListAdapter;

public class MainActivity extends AppCompatActivity implements MovieListContact.View {


    private MoviePresenter moviePresenter;
    private RecyclerView recyclerView;
    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar progressBar;



    BottomNavigationView bottomNavigationView;
    BottomNavigationItemView bottomNavigationItemView;
    BottomNavigationItemView bottomNavigationItemView1;









//    public List<Movie> movieListArray;

    private LinearLayoutManager linearLayoutManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvMovieList);
        progressBar = findViewById(R.id.progressBar);


        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationItemView = findViewById(R.id.top_rated);
        bottomNavigationItemView1 = findViewById(R.id.home);



        movieList = new ArrayList<>();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        movieListAdapter = new MovieListAdapter(movieList, this, linearLayoutManager, false);
        moviePresenter = new MoviePresenter(this);

        // Request popular movies
       moviePresenter.requestDataFromServer();

        // Request top-rated movies
        bottomNavigationItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moviePresenter.requestDataFromServer();
            }
        });
        bottomNavigationItemView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moviePresenter.getTopRatedDataFromServer();
            }
        });

        recyclerView.setAdapter(movieListAdapter);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Movie> movieListArray) {
        movieList.clear();
        // Add the new data
        movieList.addAll(movieListArray);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        if (movieListAdapter == null) {
            movieListAdapter = new MovieListAdapter(movieList, this, gridLayoutManager,false);
            recyclerView.setLayoutManager(gridLayoutManager);
            recyclerView.setAdapter(movieListAdapter);
        } else {
            // Notify the adapter that the data has changed
            movieListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("Error:", throwable.getMessage());
        Toast.makeText(this, "Error in Gating Data", Toast.LENGTH_SHORT).show();

    }


}