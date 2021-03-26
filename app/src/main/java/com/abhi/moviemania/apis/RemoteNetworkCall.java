package com.abhi.moviemania.apis;



import android.util.Log;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abhi.moviemania.models.Movies;
import com.abhi.moviemania.models.MoviesResult;

import java.util.List;


import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RemoteNetworkCall {

    private static MutableLiveData<List<MoviesResult>> data = new MutableLiveData<>();
    private static Observable<Movies> mMoviesObservable;
    private static CompositeDisposable com = new CompositeDisposable();

    public static void fetchData(String sort) {

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        mMoviesObservable = apiService.getMovies(sort, ApiClient.api_key);
        com.add(mMoviesObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Movies>() {
                    @Override
                    public void onNext(Movies movies) {
                        List<MoviesResult> results = movies.getResults();
                        data.postValue(results);

                    }

                    @Override
                    public void onError(Throwable e) {
                    Log.d("error",e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d("onComplete","complete");

                    }
                }));

    }

    public static LiveData<List<MoviesResult>> getIntData() {
        return data;
    }




}