package com.example.api1.ui;


import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api1.R;
import com.example.api1.network.YelpApi;
import com.example.api1.network.YelpClient;
import com.example.api1.models.Business;
import com.example.api1.models.Category;
import com.example.api1.models.YelpBusinessesSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {

    private TextView mTel;
    private TextView mTel2;
    private TextView mTel5;
    private String city;
    private TextView mErrorTextView;
    private int[] images = {R.drawable.action,
            R.drawable.thriller,
            R.drawable.christian,
            R.drawable.romance

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

//        mTel = (TextView) findViewById(R.id.tel);
//        mTel2 = (TextView) findViewById(R.id.tel2);
//        mTel5 = (TextView) findViewById(R.id.tel5);


        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getMovies("location", "movie");

        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    movies = response.body().getBusinesses();
                    mAdapter = new MyMoviesArrayAdapter(MoviesActivity.this, movies);

                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(MoviesActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showMovies();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();
            }

        });
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showMovies() {
        {
            mRecyclerView.setVisibility(View.VISIBLE);

        }


    }
}


