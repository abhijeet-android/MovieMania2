package com.abhi.moviemania.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abhi.moviemania.R;
import com.abhi.moviemania.adapters.RecyclerMovie;
import com.abhi.moviemania.models.MoviesResult;
import com.abhi.moviemania.util.DaggerMyComponent;
import com.abhi.moviemania.util.MyComponent;
import com.abhi.moviemania.util.SharedPrefModule;
import com.abhi.moviemania.viewmodels.MainViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mrecyclerView;
    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.collapsing_main)
    CollapsingToolbarLayout mCollapsingMain;
    private int selected = 0;
    private RecyclerMovie mRecyclerMovie;
    MainViewModel viewModel;


    private final static String MENU_SELECTED = "selected";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mCollapsingMain.setTitle("MOVIES");


        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setNestedScrollingEnabled(false);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        if (savedInstanceState == null) {
            populateUI(selected);

        } else {
            selected = savedInstanceState.getInt(MENU_SELECTED);
            populateUI(selected);
        }


    }

    private void populateUI(int i) {
        viewModel.mLiveData().removeObservers(this);

                viewModel.mLiveData().observe(this, new Observer<List<MoviesResult>>() {
                    @Override
                    public void onChanged(@Nullable List<MoviesResult> results) {
                        setupRecyclerView(results);
                    }
                });




    }

    private void setupRecyclerView(List<MoviesResult> results) {

        if (results != null) {

            mRecyclerMovie = new RecyclerMovie(results);
            mrecyclerView.setAdapter(mRecyclerMovie);
            mRecyclerMovie.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "List Null", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(MENU_SELECTED, selected);
        super.onSaveInstanceState(outState);
    }





}