package com.example.api1;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.api1.models.Business;
import com.example.api1.models.Category;
import com.example.api1.models.YelpBusinessesSearchResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesActivity extends AppCompatActivity {
    private String[] types = new String[]{"Action", "Thriller", "Christian", "Romance"};
    private TextView mTel;
    private TextView mTel2;
    private TextView mTel5;
    private String city;
    private int [] images = {R.drawable. action,
                             R.drawable. thriller,
                             R.drawable. christian,
                             R.drawable. romance

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        mTel = (TextView) findViewById(R.id.tel);
        mTel2 = (TextView) findViewById(R.id.tel2);
        mTel5 = (TextView) findViewById(R.id.tel5);

        YelpApi client = YelpClient.getClient();

        Call<YelpBusinessesSearchResponse> call = client.getMovies("USA", "movie");
        call.enqueue(new Callback<YelpBusinessesSearchResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessesSearchResponse> call, Response<YelpBusinessesSearchResponse> response) {
                if (response.isSuccessful()) {
                    List<Business> moviesList = response.body().getBusinesses();
                    String[] movies = new String[moviesList.size()];
                    String[] categories = new String[moviesList.size()];

                    for (int i = 0; i < movies.length; i++){
                        movies[i] = moviesList.get(i).getName();
                    }

                    for (int i = 0; i < categories.length; i++) {
                        Category category = moviesList.get(i).getCategories().get(0);
                        categories[i] = category.getTitle();
                    }

//                    ArrayAdapter adapter
//                            = new MyMoviesArrayAdapter(MoviesActivity.this, android.R.layout.simple_list_item_1, movies, categories);
//                    mListView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<YelpBusinessesSearchResponse> call, Throwable t) {

            }

        });


        mTel.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, ActionActivity.class);


                startActivity(intent);


            }
        });
        mTel2.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, ThrillerActivity.class);


                startActivity(intent);


            }
        });

        mTel5.setOnClickListener(new  View.OnClickListener(){


            @Override
            public void onClick(View view) {
                Intent intent1 = getIntent();
                Intent intent = new Intent(MoviesActivity.this, RomanceActivity.class);


                startActivity(intent);


            }
        });


    }
}
