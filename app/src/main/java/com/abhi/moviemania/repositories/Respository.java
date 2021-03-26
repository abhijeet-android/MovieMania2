package com.abhi.moviemania.repositories;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.abhi.moviemania.apis.RemoteNetworkCall;
import com.abhi.moviemania.models.MoviesResult;

import java.util.List;

public class Respository {


    private LiveData<List<MoviesResult>> mData;
    private LiveData<List<MoviesResult>> mDataFav;
    private int movieID;

    // constructor for movie
    public Respository(Application application) {

        RemoteNetworkCall.fetchData("popular");


    }
    public LiveData<List<MoviesResult>> mLiveData() {
        mData = RemoteNetworkCall.getIntData();
        return mData;
    }

    public LiveData<List<MoviesResult>> mLiveDataFav() {
        return mDataFav;
    }


}
