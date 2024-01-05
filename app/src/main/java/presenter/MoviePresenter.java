package presenter;

import java.util.List;

import contract.MovieListContact;
import model.Movie;
import service.MovieListModel;

public class MoviePresenter implements MovieListContact.Presenter,MovieListContact.Model.onFinishedListener {


    private MovieListContact.View movieListView;
    private MovieListContact.Model movieListModel;

    public MoviePresenter(MovieListContact.View movieListView) {
        this.movieListView = movieListView;
        movieListModel =new MovieListModel();
    }

    @Override
    public void onDestroy() {
        this.movieListView=null;

    }

    @Override
    public void getMoreData(int pageNo) {
        if(movieListView != null){
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this,pageNo+1);

    }

    @Override
    public void requestDataFromServer() {
        if(movieListView != null){
            movieListView.showProgress();
        }
        movieListModel.getMovieList(this,1);

    }

    @Override
    public void onFinished(List<Movie> movieArrayList) {
        movieListView.setDataToRecyclerView(movieArrayList);
        if(movieListView !=null){
            movieListView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {
        movieListView.onResponseFailure(t);
        if (movieListView != null) {
            movieListView.hideProgress();
        }

    }

@Override
    public void getTopRatedDataFromServer() {
        if (movieListView != null) {
            movieListView.showProgress();
        }
        movieListModel.getTopRatedMovieList(this, 1);
    }
}
