package contract;

import java.util.List;

import model.Movie;

public interface MovieListContact {

    interface Model{

       interface onFinishedListener
       {
           void onFinished(List<Movie>movieArrayList);
           void onFailure(Throwable t);
       }
       void  getMovieList( onFinishedListener onFinishedListener,int pageNo);
        void getTopRatedMovieList(onFinishedListener onFinishedListener, int pageNo); // Add this line

    }
    interface View{
        void  showProgress();
        void hideProgress();
        void setDataToRecyclerView(List<Movie>movieListArray);
        void onResponseFailure(Throwable throwable);
    }
    interface Presenter{
        void onDestroy();
        void getMoreData(int pageNo);
        void requestDataFromServer();
        void getTopRatedDataFromServer();
    }





}
