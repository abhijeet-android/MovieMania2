package com.abhi.moviemania.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Movies implements Parcelable {
//


    @SerializedName("results")
    @Expose
    private List<MoviesResult> results = null;



    public List<MoviesResult> getResults() {
        return results;
    }

    public void setResults(List<MoviesResult> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeList(this.results);
    }

    public Movies() {
    }

    protected Movies(Parcel in) {
       this.results = new ArrayList<MoviesResult>();
        in.readList(this.results, MoviesResult.class.getClassLoader());
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
