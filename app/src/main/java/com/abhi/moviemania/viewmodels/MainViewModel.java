package com.abhi.moviemania.viewmodels;

import android.app.Application;

import android.util.Log;


import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.abhi.moviemania.models.MoviesResult;
import com.abhi.moviemania.repositories.Respository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<MoviesResult>> mData;
    private LiveData<List<MoviesResult>> mDataFav;
    private Respository mRespository;

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public MainViewModel(@NonNull Application application) {
        super(application);


        mRespository = new Respository(application);

    }

    public LiveData<List<MoviesResult>> mLiveData() {
        mData = mRespository.mLiveData();
        return mData;
    }

    public LiveData<List<MoviesResult>> mLiveDataFav() {
        if (mDataFav == null) {
            mDataFav = new MutableLiveData<>();
        }
        mDataFav = mRespository.mLiveDataFav();
        return mDataFav;
    }



}
