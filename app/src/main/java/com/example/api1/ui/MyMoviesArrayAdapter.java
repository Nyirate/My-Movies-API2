package com.example.api1.ui;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class MyMoviesArrayAdapter extends AppCompatActivity {
    private Context mContext;
    private String[] mMovies;


    public MyMoviesArrayAdapter(Context mContext, String[] mMovies) {

        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @Override
    public Object getItem(int position) {
        String movie = mMovies[position];
        return String.format("%s \nGreat movie: %s", movie);
    }

    @Override
    public int getCount() {
        return mMovies.length;
    }
}



