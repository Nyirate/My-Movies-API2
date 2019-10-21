package com.example.api1.ui;


import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    private ListView mListView;
    private ProgressBar mProgressBar;
    private EditText editLocation;
    public List<Business> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mListView = (ListView) findViewById(R.id.list);
        mErrorTextView = (TextView) findViewById(R.id.error);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);



        Intent intent = getIntent();

        String location = intent.getStringExtra("location");
        Toast.makeText(this, "" + location, Toast.LENGTH_SHORT).show();

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getMovies(location, "movie");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    List<Business> moviesList = response.body().getBusinesses();
                    String[] movies = new String[moviesList.size()];
//                    Toast.makeText(MoviesActivity.this, ""+movies, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < movies.length; i++) {
                        movies[i] = moviesList.get(i).getName();
                    }

                    ArrayAdapter adapter = new MyMoviesArrayAdapter(MoviesActivity.this, android.R.layout.simple_list_item_1, movies);
                    mListView.setAdapter(adapter);
                    showMovies();
                } else {
                    showUnsuccessfulMessage();
                }
                
            }

            private void showMovies() {
                mListView.setVisibility(View.VISIBLE);
            }


            private void hideProgressBar() {
                mProgressBar.setVisibility(View.GONE);

            }


            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {
                hideProgressBar();
                showFailureMessage();

            }

            private void showFailureMessage() {
                mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
            }

            private void showUnsuccessfulMessage() {
                mErrorTextView.setText("Something went wrong. Please try again later");
                mErrorTextView.setVisibility(View.VISIBLE);
            }



        });

    }}
