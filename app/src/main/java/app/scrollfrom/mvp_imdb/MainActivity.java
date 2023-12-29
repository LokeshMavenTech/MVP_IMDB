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

import java.util.ArrayList;
import java.util.List;

import contract.MovieListContact;
import model.Movie;
import presenter.MoviePresenter;
import view.MovieListAdapter;

public class MainActivity extends AppCompatActivity implements MovieListContact.View {


    private MoviePresenter moviePresenter;
    private RecyclerView recyclerView ;
    private List<Movie> movieList;
    private MovieListAdapter movieListAdapter;
    private ProgressBar progressBar;
//    public List<Movie> movieListArray;

    private LinearLayoutManager linearLayoutManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       recyclerView = findViewById(R.id.rvMovieList);
       progressBar =findViewById(R.id.progressBar);

       movieList= new ArrayList<>();


       linearLayoutManager = new LinearLayoutManager(this);
       recyclerView.setLayoutManager(linearLayoutManager);
       recyclerView.setHasFixedSize(true);
       movieListAdapter = new MovieListAdapter(movieList, this, linearLayoutManager);
       moviePresenter = new MoviePresenter(this);
       moviePresenter.requestDataFromServer();
        recyclerView.setAdapter(movieListAdapter);

//      setDataToRecyclerView(movieListArray);
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
        movieList.addAll(movieListArray);
       // movieListAdapter=new MovieListAdapter(new GridLayoutManager(this,2));
        recyclerView.setAdapter(movieListAdapter);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        movieListAdapter = new MovieListAdapter(movieList, this, gridLayoutManager);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(movieListAdapter);



    }


    @Override
    public void onResponseFailure(Throwable throwable) {
        Log.e("Error:",throwable.getMessage());
        Toast.makeText(this, "Error in Gating Data", Toast.LENGTH_SHORT).show();

    }
}