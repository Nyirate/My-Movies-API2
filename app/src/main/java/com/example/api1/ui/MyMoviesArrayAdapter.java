package com.example.api1.ui;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MyMoviesArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mMovies;


    public MyMoviesArrayAdapter(Context mContext, int resource, String[] mMovies) {
        super(mContext, resource);

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



